package com.zhouqi.task;

import com.zhouqi.schedule.task.AbstractTask;
import com.zhouqi.schedule.task.bean.ProcessResult;

public class TestTask extends AbstractTask{
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
