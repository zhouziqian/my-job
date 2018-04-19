package com.zhouqi.schedule.trigger;

import com.zhouqi.entity.CfgTask;
import org.quartz.Trigger;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/13 9:00
 */
public interface ITriggerExpression {
    Trigger trigger(CfgTask task);

    String comment();

    String type();
}
