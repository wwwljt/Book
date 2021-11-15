package com.www.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  13:08 星期四
 * @version 11.0.9
 * @since 16
 */
public class JdbcUtils {
    
    private static DruidDataSource druidDataSource;
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    
    static {
        try {
            Properties properties = new Properties();
            
            //读取 jdbc.properties 属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            
            //从盘中加载
            properties.load(inputStream);
            
            //创建数据库连接池
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            
            //            System.out.println(druidDataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = connectionThreadLocal.get();
        //如果不等于null  ，说明之前使用过链接，操作数据库
        if (connection != null) {
            try {
                //提交事务
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭连接，释放资源
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //一定要执行 remove 操作，否则会出错(因为Tomcat 服务器使用了线程池技术)
            connectionThreadLocal.remove();
        }
    }
    
    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = connectionThreadLocal.get();
        //如果不等于null  ，说明之前使用过链接，操作数据库
        if (connection != null) {
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭连接，释放资源
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //一定要执行 remove 操作，否则会出错(因为Tomcat 服务器使用了线程池技术)
            connectionThreadLocal.remove();
        }
    }
    
    
    
    
    
    /*
     *//**
     * 关闭连接，放回数据库连接池
     *
     * @param connection 连接
     *//*
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }*/
    
    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回 null ，说明获取连接失败 ；   有值说明获取连接成功
     */
    public static Connection getConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                //从数据库连接池中获取连接
                connection = druidDataSource.getConnection();
                
                //保存到ThreadLocal对象中，共后面的jdbc操作使用
                connectionThreadLocal.set(connection);
                
                //设置为手动管理事务
                connection.setAutoCommit(false);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        return connection;
    }
    
    
}
