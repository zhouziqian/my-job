package com.zhouqi.task;

import com.zhouqi.schedule.job.impl.task.ITask;
import com.zhouqi.schedule.task.AbstractTask;
import com.zhouqi.schedule.task.bean.ProcessResult;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/17 17:26
 */
public class TestTTask extends AbstractTask {
    @Override
    protected void init(ProcessResult processSummary) throws Exception {

    }

    @Override
    protected void doBusiness(ProcessResult processSummary) throws Exception {
        System.out.println("doTask------------------------------------------------------------------------");
    }

    @Override
    protected void cleanup(ProcessResult processSummary) throws Exception {

    }
}

