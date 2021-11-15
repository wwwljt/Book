package com.www.dao.impl;

import com.www.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  13:43 星期四
 * @version 11.0.9
 * @since 16
 */
public abstract class BaseDao {
    
    //使用DbUtils 操作数据库
    private final QueryRunner queryRunner = new QueryRunner();
    
    /**
     * update()  方法用来执行 insert 、update 、delete语句
     *
     * @return 如果返回 -1，说明执行失败；   返回其他表示受影响的行数
     */
    public int update(String sql, Object... args) {
        System.out.println(" BaseDao  程序 在【" + Thread.currentThread().getName() + "】中");
        
        //获取连接
        Connection connection = JdbcUtils.getConnection();
        
        try {
            //调用 queryRunner里的 update（增删改） 方法
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            //抛出运行时异常
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询返回一个 javaBean 的 sql 语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql 语句
     * @param args sql 对应的参数值
     * @param <T>  返回类型的泛型
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        //获取链接
        Connection connection = JdbcUtils.getConnection();
        
        try {
            //调用 queryRunner 里的 query()方法 【查询】
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询返回多 个  javaBean 的 sql 语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql 语句
     * @param args sql 对应的参数值
     * @param <T>  返回类型的泛型
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        //获取链接
        Connection connection = JdbcUtils.getConnection();
        
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询返回一行一列的 sql 语句
     *
     * @param sql  执行的sql 语句
     * @param args sql 对应的参数值
     */
    public Object queryForSingleValue(String sql, Object... args) {
        //获取连接
        Connection connection = JdbcUtils.getConnection();
        
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
       
    }
}
