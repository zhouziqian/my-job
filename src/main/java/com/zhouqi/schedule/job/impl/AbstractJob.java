package com.zhouqi.schedule.job.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.entity.CfgTaskLog;
import com.zhouqi.enums.Constants;
import com.zhouqi.service.TaskLogService;
import com.zhouqi.utils.AppplicationContextUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Timestamp;

public abstract class AbstractJob implements Job {
    private static transient Logger logger = Logger.getLogger(AbstractJob.class);

    @Override
    public final void execute(JobExecutionContext context) throws JobExecutionException {
        CfgTask task = (CfgTask) context.getMergedJobDataMap().get(Constants.Schedule.KEY_TASK);
        CfgTaskLog log = new CfgTaskLog();
        log.setCfgTaskId(task.getCfgTaskId());
        log.setStartDate(new Timestamp(System.currentTimeMillis()));
        log.setState(Constants.STATE.R.name());
        log.setFinishDate(new Timestamp(System.currentTimeMillis()));
        TaskLogService taskLogService = AppplicationContextUtil.getContext().getBean(TaskLogService.class);
        try {
            taskLogService.insertSelective(log);
        } catch (Exception e) {
            System.out.println("scheduler.log.save.error" + e.getMessage());
        }

        logger.info("Start execute the task[id:" + task.getCfgTaskId() + ",taskName:" + task.getTaskName() + ",taskInstanceId:" + log.getTaskId() + "]");

        try {
            /** put the task instance id */
            context.getJobDetail().getJobDataMap().put(Constants.Schedule.KEY_TASK_INSTANCE_ID, log.getTaskId());
            String result = executeInternal(context);
            log.setContent(result);
            log.setState(Constants.STATE.N.name());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.setState(Constants.STATE.E.name());
            log.setContent(e.getMessage());
        } finally {
            log.setFinishDate(new Timestamp(System.currentTimeMillis()));
            try {
                taskLogService.updateByPrimaryKeySelective(log);
            } catch (Exception e) {
                System.out.println("scheduler.log.update.error" + log.getTaskId() + e.getMessage());
            }
            logger.info("Finish execute the task[taskInstanceId:" + log.getTaskId() + "]");
        }
    }

    abstract public String executeInternal(JobExecutionContext context) throws Exception;
}
