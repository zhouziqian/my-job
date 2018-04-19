package com.zhouqi.schedule.job.type.impl;

import com.zhouqi.schedule.job.type.IJobExpression;
import com.zhouqi.schedule.job.type.IJobTypeResolver;

import java.util.Map;

public class SimpleJobTypeResolver implements IJobTypeResolver {
    private Map<String, IJobExpression> types;

    @Override
    public IJobExpression type(String type) {
        IJobExpression je = types.get(type);
        if (je == null) {
            System.out.println("scheduler.job.not.support" + type);
        }
        return types.get(type);
    }

    public Map<String, IJobExpression> getTypes() {
        return types;
    }

    public void setTypes(Map<String, IJobExpression> types) {
        this.types = types;
    }
}
