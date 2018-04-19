package com.zhouqi.schedule.task.cfg;

import com.zhouqi.schedule.util.OutboundConfigureUtil;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/18 11:33
 */
public class TaskFileConfig extends AbstractTaskConfig {
    public TaskFileConfig(String taskName) {
        super(taskName);
    }

    @Override
    public String getAsString(String codeValue) {
        return OutboundConfigureUtil.getProperty(key(codeValue));
    }

    @Override
    public boolean contains(String codeValue) {
        try {
            OutboundConfigureUtil.getProperty(key(codeValue));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String key(String srcKey) {
        return taskName() + "." + srcKey;
    }
}
