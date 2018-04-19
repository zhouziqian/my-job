package com.zhouqi.schedule.task;

import com.zhouqi.enums.Constants;
import com.zhouqi.schedule.job.impl.task.ITask;
import com.zhouqi.schedule.task.bean.ProcessResult;
import com.zhouqi.schedule.task.cfg.ITaskConfig;
import com.zhouqi.schedule.task.cfg.TaskConfigFactory;
import com.zhouqi.utils.JedisUtil;
import com.zhouqi.utils.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zhouqi.schedule.task.cfg.TaskConfigFactory;
import com.zhouqi.schedule.task.cfg.TaskConfigFactory.TaskConfigMode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractTask implements ITask {
    private static transient Logger log = LoggerFactory.getLogger(AbstractTask.class);
    ITaskConfig taskConfig;
    ProcessResult processSummary = new ProcessResult();
    public static ConcurrentMap<String, AtomicBoolean> PORCESS_STATUS = new ConcurrentHashMap<String, AtomicBoolean>();

    public AbstractTask() {
        taskConfig = TaskConfigFactory.createTaskConfig(taskConfigMode(), taskName());
        initFlag();
    }

    protected TaskConfigMode taskConfigMode() {
        String mode = System.getProperty(TaskConstants.PropertiesName.CFG_MODE, TaskConfigMode.DB.name()).toUpperCase();
        log.info(taskName() + " task confgiure mode: " + mode);
        return TaskConfigMode.valueOf(mode);
    }

    protected void initFlag() {
        boolean isLocal = getAsBoolean(Constants.Cache.KEY_TMS_SYNC_LOCAL + "_" + getClass().getSimpleName(), true);
        if (isLocal) {
            AtomicBoolean processFlag = PORCESS_STATUS.get(getClass().getSimpleName());
            if (processFlag == null) {
                PORCESS_STATUS.putIfAbsent(getClass().getSimpleName(), new AtomicBoolean(false));
            }
        } else {
            AtomicBoolean processFlag = (AtomicBoolean) SerializeUtil.unserialize(JedisUtil.createJedis().hget(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName()).getBytes());
            if (processFlag == null) {
                JedisUtil.createJedis().hset(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName(), SerializeUtil.serialize(new AtomicBoolean(false)).toString());
            }
        }
    }

    @Override
    public String doTask(Map<String, Object> parameter) throws Exception {
        Long taskInstanceId = (Long) parameter.get(Constants.Schedule.KEY_TASK_INSTANCE_ID);
        if (taskInstanceId == null) {
            taskInstanceId = System.currentTimeMillis();
        }
        if (setRunning()) {
            long startTime = System.currentTimeMillis();
            try {
                init(processSummary);
                doBusiness(processSummary);
                cleanup(processSummary);
            } catch (Exception e) {
                log.error("doTask error taskInstanceId is {}", taskInstanceId);
                e.printStackTrace();
            } finally {
                if (!setNotRunning()) {
                    setRunning(false);
                }
                log.info("after end success is {} ", getStatusRunning());
                log.info("Sub Task " + taskInstanceId + " process took " + (System.currentTimeMillis() - startTime) + "ms.");
                log.info("Sub Task Summary" + processSummary);
            }
            return taskName() + processSummary;
        } else {
            return taskName() + "::RE-ERROR";
        }
    }

    protected boolean getStatusRunning() {
        boolean isJudge = getAsBoolean(getClass().getSimpleName(), true);
        boolean isLocal = getAsBoolean(Constants.Cache.KEY_TMS_SYNC_LOCAL + "_" + getClass().getSimpleName(), true);
        if (isJudge) {
            if (isLocal) {
                return PORCESS_STATUS.get(getClass().getSimpleName()).get();
            } else {
                AtomicBoolean atomicBoolean = (AtomicBoolean) SerializeUtil.unserialize(JedisUtil.createJedis().hget(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName()).getBytes());
                if (atomicBoolean != null) {
                    boolean flag = atomicBoolean.get();
                    return flag;
                }
            }
        }
        return false;
    }

    protected synchronized boolean setRunning() {
        boolean isLocal = getAsBoolean(Constants.Cache.KEY_TMS_SYNC_LOCAL + "_" + getClass().getSimpleName(), true);
        boolean isJudge = getAsBoolean(getClass().getSimpleName(), true);
        if (isJudge) {
            if (isLocal) {
                return PORCESS_STATUS.get(getClass().getSimpleName()).compareAndSet(false, true);
            } else {
                AtomicBoolean atomicBoolean = (AtomicBoolean) SerializeUtil.unserialize(JedisUtil.createJedis().hget(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName()).getBytes());
                if (atomicBoolean != null) {
                    boolean falg = atomicBoolean.compareAndSet(false, true);
                    JedisUtil.createJedis().hset(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName(), SerializeUtil.serialize(atomicBoolean).toString());
                    return falg;
                }
            }
        }
        return true;
    }

    protected synchronized void setRunning(boolean runningFlag) {
        boolean isJudge = getAsBoolean(getClass().getSimpleName(), true);
        boolean isLocal = getAsBoolean(Constants.Cache.KEY_TMS_SYNC_LOCAL + "_" + getClass().getSimpleName(), true);
        if (isJudge) {
            if (isLocal) {
                PORCESS_STATUS.get(getClass().getSimpleName()).set(runningFlag);
            } else {
                AtomicBoolean atomicBoolean = (AtomicBoolean) SerializeUtil.unserialize(JedisUtil.createJedis().hget(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName()).getBytes());
                if (atomicBoolean != null) {
                    atomicBoolean.set(runningFlag);
                    JedisUtil.createJedis().hset(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName(), SerializeUtil.serialize(atomicBoolean).toString());
                }
            }
        }
    }

    protected synchronized boolean setNotRunning() {
        boolean isJudge = getAsBoolean(getClass().getSimpleName(), true);
        boolean isLocal = getAsBoolean(Constants.Cache.KEY_TMS_SYNC_LOCAL + "_" + getClass().getSimpleName(), true);
        if (isJudge) {
            if (isLocal) {
                return PORCESS_STATUS.get(getClass().getSimpleName()).compareAndSet(true, false);
            } else {
                AtomicBoolean atomicBoolean = (AtomicBoolean) SerializeUtil.unserialize(JedisUtil.createJedis().hget(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName()).getBytes());
                if (atomicBoolean != null) {
                    boolean flag = atomicBoolean.compareAndSet(true, false);
                    JedisUtil.createJedis().hset(Constants.Cache.KEY_TMS_SYNC_JOB, getClass().getSimpleName(), SerializeUtil.serialize(atomicBoolean).toString());
                    return flag;
                }
            }
        }
        return true;
    }

    protected abstract void init(ProcessResult processSummary) throws Exception;

    protected abstract void doBusiness(ProcessResult processSummary) throws Exception;

    protected abstract void cleanup(ProcessResult processSummary) throws Exception;

    public String taskName() {
        return getClass().getSimpleName();
    }

    public boolean getAsBoolean(String key, boolean defaultValue) {
        return taskConfig.getAsBoolean(key, defaultValue);
    }
}
