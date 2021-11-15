package com.www.dao.impl;

import com.www.dao.UserDao;
import com.www.pojo.User;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  14:18 星期四
 * @version 11.0.9
 * @since 16
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 根据用户名 查询用户信息
     *
     * @param user 用户名
     * @return 如果返回 null ，说明没有此用户，反之亦然
     */
    @Override
    public User queryUserByUsername(User user) {
        if (user != null) {
            String sql = "select id,username,password from t_user where username=?";
            return this.queryForOne(User.class, sql, user.getUsername());
        } else {
            return null;
        }
    }
    
    /**
     * 根据用户名 和 密码 查询用户信息
     *
     * @param user 用户名 和密码
     * @return 如果返回 null ，说明用户名 或密码错误，反之亦然
     */
    @Override
    public User queryUserByUsernameAndPassWord(User user) {
        if (user != null) {
            String sql = "select id,username,password from t_user where username=? and password=?";
            return this.queryForOne(User.class, sql, user.getUsername(), user.getPassword());
        } else {
            return null;
        }
    }
    
    /**
     * 保存用户信息
     *
     * @param user user 对象
     * @return 返回 -1 ，说明保存成功，反之 返回受影响的行数
     */
    @Override
    public int saveUser(User user) {
        if (user != null) {
            String sql = "insert into t_user(username, password, email) values (?,?,?) ";
            return this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        } else {
            return -1;
        }
    }
}
