package com.zhouqi.schedule.task.cfg;

import java.io.File;
import java.sql.Timestamp;

public interface ITaskConfig {
    public String getAsString(String codeValue);

    public boolean contains(String codeValue);

    public int getAsInt(String key);

    public float getAsFloat(String key);

    public long getAsLong(String key);

    public byte getAsByte(String key);

    public char getAsChar(String key);

    public short getAsShort(String key);

    public double getAsDouble(String key);

    public boolean getAsBoolean(String key);

    public Timestamp getAsDate(String key);

    public File getAsFile(String key);

    public String getAsString(String key, String defaultValue);

    public int getAsInt(String key, int defaultValue);

    public float getAsFloat(String key, float defaultValue);

    public long getAsLong(String key, long defaultValue);

    public byte getAsByte(String key, byte defaultValue);

    public char getAsChar(String key, char defaultValue);

    public short getAsShort(String key, short defaultValue);

    public double getAsDouble(String key, double defaultValue);

    public boolean getAsBoolean(String key, boolean defaultValue);

    public String taskName();
}
