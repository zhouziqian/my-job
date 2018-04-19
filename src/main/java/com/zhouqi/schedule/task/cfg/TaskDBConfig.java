package com.zhouqi.schedule.task.cfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/18 11:26
 */
public class TaskDBConfig extends AbstractTaskConfig {
    public static String CODE_TYPE_TASK_CFG = "TASK_CFG_";
//    Map<String, CfgStaticData> config = StaticDataHelper.queryMapByTypeDB(CODE_TYPE_TASK_CFG + taskName());
    ;
    Map<String, Object> config = new HashMap<>();

    @Override
    public String key(String srcKey) {
        return srcKey;
    }

    @Override
    public String getAsString(String codeValue) {
//        CfgStaticData bo = config.get(codeValue);
        Object bo = config.get(codeValue);
        if (bo != null) {
//            return bo.getDataValue();
            return bo.toString();
        } else {
            throw new IllegalArgumentException("The value with key " + codeValue + " not exists!");
        }
    }

    public TaskDBConfig(String taskName) {
        super(taskName);
    }

    public Set<String> keys() {
        return config.keySet();
    }

    @Override
    public boolean contains(String codeValue) {
        return config.containsKey(codeValue);
    }
}
