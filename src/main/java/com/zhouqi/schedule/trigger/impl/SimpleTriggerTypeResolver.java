package com.zhouqi.schedule.trigger.impl;

import com.zhouqi.schedule.trigger.ITriggerExpression;
import com.zhouqi.schedule.trigger.ITriggerTypeResolver;

import java.util.Map;

public class SimpleTriggerTypeResolver implements ITriggerTypeResolver {
    private Map<String, ITriggerExpression> types;

    @Override
    public ITriggerExpression type(String type) {
        ITriggerExpression te = types.get(type);
        if (te == null) {
            System.out.println("scheduler.trigger.not.support " + type);
        }
        return types.get(type);
    }

    public Map<String, ITriggerExpression> getTypes() {
        return types;
    }

    public void setTypes(Map<String, ITriggerExpression> types) {
        this.types = types;
    }
}
