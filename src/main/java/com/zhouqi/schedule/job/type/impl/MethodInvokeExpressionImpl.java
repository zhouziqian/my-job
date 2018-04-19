package com.zhouqi.schedule.job.type.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.impl.method.MethodInvokeJobImpl;
import com.zhouqi.schedule.job.type.impl.ISimpleJobExpression;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobBuilder;

public class MethodInvokeExpressionImpl extends ISimpleJobExpression {
    @Override
    public void build(JobBuilder builder, CfgTask task) {
        builder.ofType(MethodInvokeJobImpl.class);
        String jobExpr = task.getJobExpr();

        builder.usingJobData(Constants.Schedule.KEY_TASK_TYPE_CLASSNAME, StringUtils.substringBefore(jobExpr, " ").trim());
        builder.usingJobData(Constants.Schedule.KEY_TASK_TYPE_METHOD, StringUtils.substringAfter(jobExpr, " ").trim());
    }
}
