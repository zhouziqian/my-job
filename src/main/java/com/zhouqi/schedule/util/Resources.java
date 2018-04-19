package com.zhouqi.schedule.util;

import java.io.InputStream;

public class Resources {
    private Resources() {

    }

    public static ClassLoader getClassLoader() {
        ClassLoader loader = Resources.class.getClassLoader();
        if (loader == null)
            loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        return loader;
    }

    public static InputStream getAsStream(String path) {
        ClassLoader loader = getClassLoader();
        return loader.getResourceAsStream(path);
    }
}
