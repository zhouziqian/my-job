package com.zhouqi.schedule;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.type.IJobExpression;
import com.zhouqi.schedule.job.type.IJobTypeResolver;
import com.zhouqi.schedule.job.type.impl.*;
import com.zhouqi.schedule.trigger.ITriggerExpression;
import com.zhouqi.schedule.trigger.ITriggerTypeResolver;
import com.zhouqi.schedule.trigger.impl.CronTriggerExpression;
import com.zhouqi.schedule.trigger.impl.SimpleTriggerExpression;
import com.zhouqi.schedule.trigger.impl.SimpleTriggerTypeResolver;
import com.zhouqi.schedule.util.CollectionHelper;
import com.zhouqi.schedule.util.IFilter;
import com.zhouqi.schedule.util.PropertiesUtil;
import com.zhouqi.schedule.util.ScheduleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.*;

public class SchedulerManager {
    private static transient Logger logger = Logger.getLogger(SchedulerManager.class);
    private static SchedulerManager schedulerManager;
    private SchedulerFactory factory;
    private Scheduler scheduler;
    private ITriggerTypeResolver triggerTypeResolver;
    private IJobTypeResolver jobTypeResolver;
    private String path;

    public Date schedule(CfgTask cfgTask) {
        try {
            JobKey jk = ScheduleUtils.jobKey(cfgTask);
            TriggerKey tk = ScheduleUtils.triggerKey(cfgTask);
            JobDetail jd = null;
            Trigger trigger = null;
            CfgTask srcCfgTask = null;
            JobDataMap jobMap = null;
            boolean shouldSchedule = true;
            if (scheduler().checkExists(jk)) {
                jd = scheduler().getJobDetail(jk);
                trigger = scheduler.getTrigger(tk);
                jobMap = jd.getJobDataMap();
                srcCfgTask = (CfgTask) jobMap.get(Constants.Schedule.KEY_TASK);
                if (cfgTask.valueEquals(srcCfgTask)) {
                    shouldSchedule = false;

                } else {
                    logger.error("Task configure change,unschedule the task id " + cfgTask.getCfgTaskId());
                    List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
                    for (JobExecutionContext ctx : executingJobs) {
                        if (jk.equals(ctx.getJobDetail().getKey())) {
                            logger.error("Task[" + jk + "] executing,The modify will validate at the next time.");
                            return new Date();
                        }
                    }
                    scheduler().unscheduleJob(tk);
                }
            }
            if (shouldSchedule) {
                logger.info("schedule the task " + cfgTask);
                jd = getJobTypeResolver().type(cfgTask.getJobType()).jobDetail(cfgTask);
                trigger = getTriggerTypeResolver().type(cfgTask.getTriggerType()).trigger(cfgTask);
                if (Constants.STATE.N.name().equals(cfgTask.getTaskState())) {

                    return scheduler().scheduleJob(jd, trigger);
                } else {
                    logger.error("unscheduler task " + cfgTask);
                    scheduler().unscheduleJob(tk);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("scheduler.job.schedule.error" + cfgTask + e.getMessage());
        }
        return new Date();
    }

    public boolean unschedule(CfgTask cfgTask) {
        JobKey jk = ScheduleUtils.jobKey(cfgTask);
        TriggerKey tk = ScheduleUtils.triggerKey(cfgTask);
        try {
            if (scheduler().checkExists(jk)) {
                scheduler().unscheduleJob(tk);
                scheduler().deleteJob(jk);
            }
            logger.error("Sucessfully unschedule the task " + cfgTask);
            return true;
        } catch (SchedulerException e) {
            logger.error("Fail unschedule the task " + cfgTask + ".with error " + e.getMessage());
        }
        return false;
    }

    public void initMethod() {
        schedulerManager = new SchedulerManager();
        schedulerManager().setPath(path);
        SimpleTriggerTypeResolver str = new SimpleTriggerTypeResolver();
        Map<String, ITriggerExpression> types = new HashMap<>();
        types.put(Constants.Schedule.TriggerType.S.name(), new SimpleTriggerExpression());
        types.put(Constants.Schedule.TriggerType.C.name(), new CronTriggerExpression());
        str.setTypes(types);
        schedulerManager.setTriggerTypeResolver(str);


        SimpleJobTypeResolver jobr = new SimpleJobTypeResolver();
        Map<String, IJobExpression> jtypes = new HashMap<>();

        jtypes.put(Constants.Schedule.JobType.C.name(), new DirectlyImplementJobExpressionImpl());
        jtypes.put(Constants.Schedule.JobType.M.name(), new MethodInvokeExpressionImpl());
        jtypes.put(Constants.Schedule.JobType.T.name(), new DoTaskExpressionImpl());
        jtypes.put(Constants.Schedule.JobType.G.name(), new GroupTaskExpressionImpl());
        jobr.setTypes(jtypes);
        schedulerManager.setJobTypeResolver(jobr);

        schedulerManager.init();
    }

    public void init() {
        String cfgPath = path;
        if (StringUtils.isEmpty(cfgPath)) {
            cfgPath = System.getProperty("scheduler.file", "scheduler/quartz/quartz.properties");
        }
        Properties props = PropertiesUtil.getProperties(cfgPath);
        props.putAll(System.getProperties());
        try {
            factory = new StdSchedulerFactory(props);
            scheduler = factory.getScheduler();
            scheduler.start();
            checkInit();
            /**
             * 增添一个钩子方法，在进程退出时候，gracefully shutdown,避免数据不一致
             */
            Runtime.getRuntime().addShutdownHook(
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                if (!scheduler.isShutdown()) {
                                    logger.error("shutdown scheduler [" + scheduler.getSchedulerName() + "," + scheduler.getSchedulerInstanceId() + "]");
                                    scheduler.shutdown(true);
                                }
                            } catch (SchedulerException e) {
                                logger.error("can not shutdown the scheduler cause by " + e.getMessage());
                            }
                        }
                    }
            );
        } catch (SchedulerException e) {
            logger.error(e.toString());
            throw new RuntimeException("scheduler.init.error", e);
        }
    }

    private void checkInit() {
        Scheduler s = scheduler();
        try {
            List<CfgTask> cfgTasks = this.allTasks();
            if (cfgTasks != null && cfgTasks.size() > 0) {
                for (CfgTask cfgTask : cfgTasks) {
                    if (s.checkExists(ScheduleUtils.jobKey(cfgTask))) {
                        if (cfgTask.getTaskState().equals(Constants.Schedule.TaskState.S.name())) {
                            s.pauseJob(ScheduleUtils.jobKey(cfgTask));
                            s.deleteJob(ScheduleUtils.jobKey(cfgTask));
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public List<CfgTask> allTasks() {
        Scheduler s = scheduler();
        ArrayList<CfgTask> tasks = new ArrayList<CfgTask>();
        try {
            List<String> gns = s.getJobGroupNames();
            for (String gn : gns) {
                tasks.addAll(queryScheduleTasksByGroup(gn));
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<CfgTask> queryScheduleTasksByGroup(String groupId) {
        Scheduler s = scheduler();
        ArrayList<CfgTask> tasks = new ArrayList<>();
        try {
            if (StringUtils.isNotEmpty(groupId)) {
                Set<JobKey> jks = s.getJobKeys(GroupMatcher.jobGroupEquals(groupId));
                return CollectionHelper.filter(jks, tasks, new IFilter<JobKey, CfgTask>() {

                    @Override
                    public CfgTask filter(JobKey s, int index) {
                        CfgTask task = null;
                        JobDetail jd;
                        try {
                            jd = scheduler().getJobDetail(s);
                            if (jd != null) {
                                JobDataMap jobMap = jd.getJobDataMap();
                                task = (CfgTask) jobMap.get(Constants.Schedule.KEY_TASK);
                            }
                        } catch (SchedulerException e) {
                            e.printStackTrace();
                        }
                        return task;
                    }
                });
            }
        } catch (SchedulerException e) {
        }
        return tasks;
    }

    private Scheduler scheduler() {
        return this.scheduler;
    }

    public void setTriggerTypeResolver(ITriggerTypeResolver triggerTypeResolver) {
        this.triggerTypeResolver = triggerTypeResolver;
    }

    public void setJobTypeResolver(IJobTypeResolver jobTypeResolver) {
        this.jobTypeResolver = jobTypeResolver;
    }

    public static SchedulerManager schedulerManager() {
        return schedulerManager;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public IJobTypeResolver getJobTypeResolver() {
        return jobTypeResolver;
    }

    public ITriggerTypeResolver getTriggerTypeResolver() {
        return triggerTypeResolver;
    }
}
