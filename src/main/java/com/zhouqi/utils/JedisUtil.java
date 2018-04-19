package com.zhouqi.utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class JedisUtil {
    public static byte[] NULL_BYTE = new byte[0];

    public static Jedis createJedis() {
        Jedis jedis = new Jedis("127.0.0.1");
        return jedis;
    }

    public static Jedis createJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);

        return jedis;
    }

    public static Jedis createJedis(String host, int port, String passwrod) {
        Jedis jedis = new Jedis(host, port);

        if (!StringUtils.isNotBlank(passwrod)) {
            jedis.auth(passwrod);
        }

        return jedis;
    }
}
