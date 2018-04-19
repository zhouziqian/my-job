package com.zhouqi.schedule.job.type.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.impl.AbstractJob;
import org.apache.commons.lang3.ClassUtils;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;

public class DirectlyImplementJobExpressionImpl extends ISimpleJobExpression{
    @Override
    public void build(JobBuilder builder, CfgTask task) {
        Class<?> taskImpl;
        try {
            taskImpl = ClassUtils.getClass(task.getJobExpr().trim());
            if (!Job.class.isAssignableFrom(taskImpl)) {
                System.out.println("scheduler.job.type.job.class.mismatch"+task.getJobExpr());
            }
            builder.usingJobData(Constants.Schedule.KEY_TASK_TYPE_CLASSNAME, task.getJobExpr());
            builder.ofType(DelegateTask.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class DelegateTask extends AbstractJob {
        private String className;

        @Override
        public String executeInternal(JobExecutionContext context) throws Exception {
            Class<?> taskImpl = ClassUtils.getClass(getClassName());
            Job job = (Job) taskImpl.newInstance();
            job.execute(context);
            return "Sucessfully!";
        }

        public String getClassName() {

            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
