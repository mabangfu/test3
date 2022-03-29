package com.chinasoft.usermanager.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedis数据库的工具类
 */
public class JedisPoolUtil {
    private static JedisPool jedisPool;

    static {
        InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxId")));
        config.setMinIdle(Integer.parseInt(pro.getProperty("minId")));
//        初始化连接池对象
        jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
