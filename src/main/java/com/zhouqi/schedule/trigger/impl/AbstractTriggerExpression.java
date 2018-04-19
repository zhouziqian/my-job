package com.zhouqi.schedule.trigger.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.schedule.trigger.ITriggerExpression;
import com.zhouqi.schedule.util.ScheduleUtils;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

public abstract class AbstractTriggerExpression implements ITriggerExpression {
    @Override
    public final Trigger trigger(CfgTask task) {
        TriggerBuilder builder = TriggerBuilder.newTrigger();
        if (task.getStartDate() != null && task.getStartDate().after((new Date()))) {
            builder.startAt(task.getStartDate());
        }

        if (task.getEndDate() != null && task.getEndDate().after((new Date()))) {
            builder.endAt(task.getEndDate());
        }

        builder.forJob(ScheduleUtils.jobKey(task));
        builder.withIdentity(ScheduleUtils.triggerKey(task));
        triggerBuilder(builder, task);
        return builder.build();
    }

    public abstract void triggerBuilder(TriggerBuilder builder, CfgTask task);
}
