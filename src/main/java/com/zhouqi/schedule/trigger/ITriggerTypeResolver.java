package com.zhouqi.schedule.trigger;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/13 8:59
 */
public interface ITriggerTypeResolver {
    ITriggerExpression type(String type);
}
