package com.zhouqi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Timestamp;
import java.util.List;

public class JsonUtil {
    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;
    static
    {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(java.util.Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));
    }

    /**
     * 对象转json字符串
     * @param o
     * @return
     */
    public static String toString(Object o)
    {
        if (o != null)
        {
            return JSON.toJSONString(o);
        }
        return null;
    }

    /**
     * json字符串转对象
     * @param str
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T toBean(String str, Class<T> t)
    {
        return JSON.parseObject(str, t);
    }

    /**
     * json字符串转List对象
     * @param str
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> toListBean(String str, Class<T> t)
    {
        return JSON.parseArray(str, t);
    }

    /**
     * List对象转Json字符串
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toListString(List<T> t){
        return JSON.toJSONString(t);
    }
}
