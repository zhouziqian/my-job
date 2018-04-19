package com.zhouqi.schedule.job.impl.task;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.entity.CfgTaskDetail;
import com.zhouqi.entity.CfgTaskLog;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.impl.AbstractJob;
import com.zhouqi.service.TaskDetailService;
import com.zhouqi.service.TaskLogService;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.List;

public class GroupTaskJobImpl extends AbstractJob {
    private static transient Logger logger = Logger.getLogger(GroupTaskJobImpl.class);
    private String groupName;

    @Override
    public String executeInternal(JobExecutionContext context) throws Exception {
        if(StringUtils.isEmpty(groupName))
            System.out.println("scheduler.job.type.task.group cannot be null");
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        TaskLogService taskLogService = wac.getBean(TaskLogService.class);
        TaskDetailService taskDetailService = wac.getBean(TaskDetailService.class);
        List<CfgTaskDetail> details = taskDetailService.selectByGroupName(groupName);
        if(details!=null && details.size()>0){
            CfgTask t = (CfgTask)context.getMergedJobDataMap().get(Constants.Schedule.KEY_TASK);
            long startTime = System.currentTimeMillis();
            for(CfgTaskDetail d:details){
                if(Constants.STATE.N.name().equals(d.getStatus())){
                    if(StringUtils.isEmpty(d.getTriggerExp()))
                        continue;
                    CfgTaskLog log = new CfgTaskLog();
                    try{
                        log.setCfgTaskId(t.getCfgTaskId());
                        log.setCfgTaskDetailId(d.getId());
                        log.setStartDate(new Timestamp(System.currentTimeMillis()));
                        log.setState(Constants.STATE.R.name());
                        log.setFinishDate(new Timestamp(System.currentTimeMillis()));
                        taskLogService.insertSelective(log);
                        logger.error("begin to execute task :"+d.getTaskName());
                        Class<?> taskImpl = ClassUtils.getClass(d.getTriggerExp().trim());
                        if(!ITask.class.isAssignableFrom(taskImpl))
                        {
                            log.setState(Constants.STATE.E.name());
                            log.setContent("scheduler.job.type.task.class.mismatch:"+d.getTriggerExp());
                            log.setFinishDate(new Timestamp(System.currentTimeMillis()));
                            taskLogService.updateByPrimaryKeySelective(log);
                            logger.error("ERROR:scheduler.job.type.task.class.mismatch:"+d.getTriggerExp());
                            continue;
                        }
                        ITask task = null;
                        try {
                            task = (ITask)taskImpl.newInstance();
                        }catch (Exception e){
                            e.printStackTrace();
                            log.setState(Constants.STATE.E.name());
                            log.setContent(e.getMessage());
                            log.setFinishDate(new Timestamp(System.currentTimeMillis()));
                            taskLogService.updateByPrimaryKeySelective(log);
                            logger.error("ERROR:scheduler "+d.getTriggerExp() +"cannot be Instanced");
                            continue;
                        }
                        String result = task.doTask(context.getMergedJobDataMap());
                        log.setContent(result);
                        log.setState(Constants.STATE.N.name());
                        log.setFinishDate(new Timestamp(System.currentTimeMillis()));
                        taskLogService.updateByPrimaryKeySelective(log);
                    }catch(Exception e){
                        log.setState(Constants.STATE.E.name());
                        log.setContent("ERROR: "+ e.getMessage());
                        log.setFinishDate(new Timestamp(System.currentTimeMillis()));
                        taskLogService.updateByPrimaryKeySelective(log);
                        logger.error("ERROR:cannot execute "+d.getTriggerExp());
                        logger.error(e.getMessage());
                    }
                }
            }
            logger.error("Task Group "+((CfgTask)context.getMergedJobDataMap().get(Constants.Schedule.KEY_TASK)).getTaskGroup()+" process took "+(System.currentTimeMillis()-startTime)+"ms.");
            return "SUCCESS!!";
        }
        return "NO TASK……";
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
