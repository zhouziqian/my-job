package com.zhouqi.schedule.job.type.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.impl.task.GroupTaskJobImpl;
import org.quartz.JobBuilder;

public class GroupTaskExpressionImpl extends ISimpleJobExpression{
    @Override
    public void build(JobBuilder builder, CfgTask task) {
        builder.ofType(GroupTaskJobImpl.class);
        builder.usingJobData(Constants.Schedule.KEY_TASK_TYPE_GROUP, task.getTaskGroup());
    }
}
