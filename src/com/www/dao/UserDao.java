package com.www.dao;

import com.www.pojo.User;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  14:05 星期四
 * @version 11.0.9
 * @since 16
 */
public interface UserDao {
    
    /**
     * 根据用户名 查询用户信息
     *
     * @return 如果返回 null ，说明没有此用户，反之亦然
     */
    User queryUserByUsername(User user);
    
    /**
     * 根据用户名 和 密码 查询用户信息
     *
     * @return 如果返回 null ，说明用户名 或密码错误，反之亦然
     */
    User queryUserByUsernameAndPassWord(User user);
    
    /**
     * 保存用户信息
     *
     * @return 如果返回 -1 ，说明保存 失败，反之，返回受影响的行数
     */
    int saveUser(User user);
    
}
