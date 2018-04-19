package com.zhouqi.schedule.job.type;

public interface IJobTypeResolver {
    IJobExpression type(String type);
}
