package com.www.service;

import com.www.pojo.User;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  14:45 星期四
 * @version 11.0.9
 * @since 16
 */
public interface UserService {
    
    /**
     * 注册用户
     *
     * @param user 实体类对象
     */
    void registerUser(User user);
    
    /**
     * 登录
     *
     * @param user 实体类对象（用户名  和 密码）
     * @return 返回 null，登录失败；反之，登录成功
     */
    
    User login(User user);
    
    /**
     * 检查 用户名是否可用
     *
     * @param username 用户名
     * @return 返回 true 表示用户已存在 不可用，返回 false 表示用户名可用
     */
    boolean existsUsername(User username);
    
}
