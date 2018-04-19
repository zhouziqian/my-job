package com.zhouqi.schedule.task.cfg;

public class TaskConfigFactory {
    private TaskConfigFactory() {

    }

    public static AbstractTaskConfig createTaskConfig(TaskConfigMode mode,
                                                      String taskName) {
        switch (mode) {
            case DB:
                return new TaskDBConfig(taskName);
            case FILE:
                return new TaskFileConfig(taskName);
            default:
                return new TaskFileConfig(taskName);
        }
    }

    public enum TaskConfigMode {
        DB, FILE
    }
}
