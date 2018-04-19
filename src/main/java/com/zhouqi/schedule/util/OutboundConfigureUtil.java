package com.zhouqi.schedule.util;

import java.util.ResourceBundle;

public class OutboundConfigureUtil {
    public static String NOT_FOUNDED = "NOT_FOUND";
    private static ResourceBundle interfaceConfig = ResourceBundle.getBundle("system/outbound/outbound");

    public static String getRequiredProperty(String key) {
        return interfaceConfig.getString(key);
    }

    public static String getProperty(String key, String def) {
        try {
            return interfaceConfig.getString(key);
        } catch (Exception e) {
            return def;
        }
    }

    public static String getProperty(String key) {
        try {
            return interfaceConfig.getString(key);
        } catch (Exception e) {
            return null;
        }
    }
}
