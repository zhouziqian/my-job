package com.zhouqi.schedule.job.impl.task;

import com.zhouqi.schedule.job.impl.AbstractJob;
import org.apache.commons.lang3.ClassUtils;
import org.quartz.JobExecutionContext;

public class TaskJobImpl extends AbstractJob {
    private String className;

    @Override
    public String executeInternal(JobExecutionContext context) throws Exception {
        Class<?> taskImpl = ClassUtils.getClass(getClassName());
        if (!ITask.class.isAssignableFrom(taskImpl)) {
            System.out.println("scheduler.job.type.task.class.mismatch" + getClassName());
        }
        ITask task = null;
        try {
            task = (ITask) taskImpl.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return task.doTask(context.getMergedJobDataMap());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
