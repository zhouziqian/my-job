package com.zhouqi.schedule.task.bean;

import java.util.ArrayList;

public class ProcessResult {
    private boolean isSuccess = true;
    private ArrayList<String> exceptionsMsgs = new ArrayList<String>(1);

    public boolean add(String eMsg) {
        isSuccess = false;
        return exceptionsMsgs.add(eMsg);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return isSuccess ? ":OK!" : ":ERROR!";
    }

    public String getReason() {
        if (exceptionsMsgs.size() <= 0) {
            return "[Successfully!]";
        } else {
            return exceptionsMsgs.toString();
        }
    }
}
