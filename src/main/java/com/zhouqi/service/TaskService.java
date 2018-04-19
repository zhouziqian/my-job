package com.zhouqi.service;

import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/12 14:57
 */
public interface TaskService {
    CfgTask selectByPrimaryKey(Long cfgTaskId);

    void updateByPrimaryKeySelective(CfgTask cfgTask);

    void insertSelective(CfgTask cfgTask);

    boolean modifyTaskState(Long taskId, Constants.Schedule.TaskState taskState);
}
