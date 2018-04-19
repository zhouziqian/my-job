package com.zhouqi.schedule.util;

import com.zhouqi.entity.CfgTask;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class ScheduleUtils {
    public static JobKey jobKey(CfgTask task) {
        return new JobKey(task.getCfgTaskId() + "", task.getTaskGroup());
    }

    public static TriggerKey triggerKey(CfgTask task) {
        return new TriggerKey(task.getCfgTaskId() + "", task.getTaskGroup());
    }
}
