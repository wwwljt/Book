package com.www.service.impl;

import com.www.dao.UserDao;
import com.www.dao.impl.UserDaoImpl;
import com.www.pojo.User;
import com.www.service.UserService;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  16:10 星期四
 * @version 11.0.9
 * @since 16
 */
public class UserServiceImpl implements UserService {
    
    UserDao userDao = new UserDaoImpl();
    
    /**
     * 注册用户
     *
     * @param user 实体类对象
     */
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }
    
    /**
     * 登录
     *
     * @param user 实体类对象(用户名 和 密码)
     * @return 返回 null，登录失败；反之，登录成功
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassWord(user);
    }
    
    /**
     * 检查 用户名是否可用
     *
     * @param username 用户名
     * @return 返回 true 表示用户已存在 不可用， 返回 false 表示用户名可用
     */
    @Override
    public boolean existsUsername(User username) {
        //等于 null 说明，没查询到。  没查询到 说明 可用
        //不为 null ，说明 查询到， 说明已存在，不可用
        return userDao.queryUserByUsername(username) != null;
    }
}
