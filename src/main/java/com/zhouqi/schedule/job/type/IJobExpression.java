package com.zhouqi.schedule.job.type;

import com.zhouqi.entity.CfgTask;
import org.quartz.JobDetail;

public interface IJobExpression {
    JobDetail jobDetail(CfgTask task);
}
