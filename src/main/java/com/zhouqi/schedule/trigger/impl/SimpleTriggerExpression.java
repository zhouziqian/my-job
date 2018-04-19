package com.zhouqi.schedule.trigger.impl;

import com.zhouqi.entity.CfgTask;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/13 9:19
 */
public class SimpleTriggerExpression extends AbstractTriggerExpression{
    @Override
    public void triggerBuilder(TriggerBuilder builder, CfgTask task) {
        String expression = task.getTriggerExpr();
        SimpleScheduleBuilder simpleBuilder = SimpleScheduleBuilder.simpleSchedule();
        if (!StringUtils.isEmpty(expression)) {
            String[] parts = expression.trim().split(" ");
            if (parts.length > 0) {
                simpleBuilder.withIntervalInMilliseconds(Integer.parseInt(parts[0]) * 60 * 1000);
            }
            if (parts.length > 1) {
                simpleBuilder.withRepeatCount(Integer.parseInt(parts[1]));
            }
        } else {
            simpleBuilder.withRepeatCount(1);
        }
        builder.withSchedule(simpleBuilder);
        builder.withPriority((int) task.getPripority());
    }

    @Override
    public String comment() {
        return "The format is [IntervalInSeconds RepeatCount] default just shot one";
    }

    @Override
    public String type() {
        return "S";
    }
}
