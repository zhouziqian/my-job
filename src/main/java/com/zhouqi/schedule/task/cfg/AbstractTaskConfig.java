package com.zhouqi.schedule.task.cfg;

import java.io.File;
import java.sql.Timestamp;

public abstract class AbstractTaskConfig implements ITaskConfig {
    private String taskName;

    public AbstractTaskConfig(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public int getAsInt(String key) {
        String strVal = getAsString(key);
        return Integer.valueOf(strVal.trim());
    }

    @Override
    public float getAsFloat(String key) {
        String strVal = getAsString(key);
        return Float.valueOf(strVal.trim());
    }

    @Override
    public long getAsLong(String key) {
        String strVal = getAsString(key);
        return Long.valueOf(strVal.trim());
    }

    @Override
    public byte getAsByte(String key) {
        String strVal = getAsString(key);
        return Byte.valueOf(strVal.trim());
    }


    @Override
    public char getAsChar(String key) {
        String strVal = getAsString(key);
        return strVal.trim().charAt(0);
    }

    @Override
    public short getAsShort(String key) {
        String strVal = getAsString(key);
        return Short.valueOf(strVal.trim());
    }


    @Override
    public double getAsDouble(String key) {
        String strVal = getAsString(key);
        return Double.valueOf(strVal.trim());
    }


    @Override
    public boolean getAsBoolean(String key) {
        String strVal = getAsString(key);
        return Boolean.valueOf(strVal);
    }

    @Override
    public int getAsInt(String key, int defaultValue) {

        return contains(key) ? getAsInt(key) : defaultValue;
    }

    @Override
    public float getAsFloat(String key, float defaultValue) {

        return contains(key) ? getAsFloat(key) : defaultValue;
    }

    @Override
    public long getAsLong(String key, long defaultValue) {

        return contains(key) ? getAsLong(key) : defaultValue;
    }

    @Override
    public byte getAsByte(String key, byte defaultValue) {

        return contains(key) ? getAsByte(key) : defaultValue;
    }

    @Override
    public char getAsChar(String key, char defaultValue) {

        return contains(key) ? getAsChar(key) : defaultValue;
    }

    @Override
    public short getAsShort(String key, short defaultValue) {

        return contains(key) ? getAsShort(key) : defaultValue;
    }

    @Override
    public double getAsDouble(String key, double defaultValue) {

        return contains(key) ? getAsDouble(key) : defaultValue;
    }

    @Override
    public boolean getAsBoolean(String key, boolean defaultValue) {

        return contains(key) ? getAsBoolean(key) : defaultValue;
    }

    @Override
    public String getAsString(String key, String defaultValue) {

        return contains(key) ? getAsString(key) : defaultValue;
    }


    @Override
    public Timestamp getAsDate(String key) {
        String strVal = getAsString(key);
        return Timestamp.valueOf(strVal);
    }


    @Override
    public File getAsFile(String key) {
        String strVal = getAsString(key);
        if (strVal != null) {
            return new File(strVal);
        }
        return null;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String taskName() {
        return this.taskName;
    }

    public abstract String key(String srcKey);
}
