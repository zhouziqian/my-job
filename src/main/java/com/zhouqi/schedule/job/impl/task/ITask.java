package com.zhouqi.schedule.job.impl.task;

import java.util.Map;

public interface ITask {
    String doTask(Map<String,Object> parameter) throws Exception;
}
