package com.zhouqi.schedule.trigger.impl;

import com.zhouqi.entity.CfgTask;
import org.quartz.CronScheduleBuilder;
import org.quartz.TriggerBuilder;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/13 9:51
 */
public class CronTriggerExpression extends AbstractTriggerExpression {
    @Override
    public void triggerBuilder(TriggerBuilder builder, CfgTask task) {
        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(task.getTriggerExpr());
        builder.withSchedule(cronBuilder);
    }

    @Override
    public String comment() {
        return "The format is [cron expression]";
    }

    @Override
    public String type() {
        return "C";
    }
}
