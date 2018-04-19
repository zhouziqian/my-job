package com.zhouqi.schedule.job.type.impl;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.type.IJobExpression;
import com.zhouqi.schedule.util.ScheduleUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;

public abstract class ISimpleJobExpression implements IJobExpression {
    @Override
    public final JobDetail jobDetail(CfgTask task) {
        JobBuilder builder = JobBuilder.newJob();
        JobKey jk = ScheduleUtils.jobKey(task);
        builder.withIdentity(jk);
        build(builder, task);
        JobDataMap map = new JobDataMap();
//		map.put(Constants.Schedule.KEY_TASK, task.clone());
        map.put(Constants.Schedule.KEY_TASK, task);
        initJobDataMap(map, task);
        builder.usingJobData(map);
        return builder.build();
    }

    public abstract void build(JobBuilder builder, CfgTask task);

    protected void initJobDataMap(JobDataMap map, CfgTask task) {

        String params = task.getTaskParams();
        if (!StringUtils.isEmpty(params)) {
            String[] parts = params.split("&");
            for (String part : parts) {
                String name = StringUtils.substringBefore(part, "=");
                String value = StringUtils.substringAfter(part, "=");
                map.put(name, value);
            }
        }
    }
}
