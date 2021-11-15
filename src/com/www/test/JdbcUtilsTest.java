package com.www.test;

import com.www.utils.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  13:37 星期四
 * @version 11.0.9
 * @since 16
 */
public class JdbcUtilsTest {
    
    /**
     * 获取连接测试
     */
    @Test
    public void test() {
        /*for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }
}
