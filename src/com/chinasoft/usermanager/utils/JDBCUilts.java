package com.chinasoft.usermanager.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类
 * 1.c处理数据库的连接
 * 2.处理资源的关闭
 */
public class JDBCUilts {

    //连接池
    private static DataSource ds;

    /**
     *  Properties
     *
     */
    static{
        try {
            Properties properties = new Properties();
            InputStream in = JDBCUilts.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(in);
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池
     */
    public static DataSource getDataSource(){

        return ds;
    }

}
