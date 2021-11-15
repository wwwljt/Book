package com.www.test;

import com.www.dao.UserDao;
import com.www.dao.impl.UserDaoImpl;
import com.www.pojo.User;
import org.junit.jupiter.api.Test;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  14:33 星期四
 * @version 11.0.9
 * @since 16
 */
class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    
    @Test
    void queryUserByUsername() {
        if (userDao.queryUserByUsername(new User("admin")) == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在");
        }
    }
    
    @Test
    void queryUserByUsernameAndPassWord() {
        if (userDao.queryUserByUsernameAndPassWord(new User("admin", "admin1")) == null) {
            System.out.println("用户名 或  密码 错误！ 登录失败！");
        } else {
            System.out.println("用户名 或  密码  正确！  登录成功！");
        }
    }
    
    @Test
    void saveUser() {
        System.out.println(userDao.saveUser(new User("123", "123", "123@com.www.com")));
    }
}