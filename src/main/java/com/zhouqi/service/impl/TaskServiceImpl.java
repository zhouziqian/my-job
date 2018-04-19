package com.zhouqi.service.impl;

import com.zhouqi.dao.CfgTaskMapper;
import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.Constants;
import com.zhouqi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/12 14:58
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private CfgTaskMapper cfgTaskMapper;

    @Override
    public CfgTask selectByPrimaryKey(Long cfgTaskId) {
        return cfgTaskMapper.selectByPrimaryKey(cfgTaskId);
    }

    @Override
    public void updateByPrimaryKeySelective(CfgTask cfgTask) {
        cfgTaskMapper.updateByPrimaryKeySelective(cfgTask);
    }

    @Override
    public void insertSelective(CfgTask cfgTask) {
        cfgTaskMapper.insertSelective(cfgTask);
    }

    @Override
    public boolean modifyTaskState(Long taskId, Constants.Schedule.TaskState taskState) {
        CfgTask cfgTask = new CfgTask();
        cfgTask.setCfgTaskId(taskId);
        cfgTask.setTaskState(taskState.name());
        cfgTaskMapper.updateByPrimaryKeySelective(cfgTask);
        return true;
    }
}
