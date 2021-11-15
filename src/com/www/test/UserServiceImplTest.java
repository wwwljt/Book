package com.www.test;

import com.www.pojo.User;
import com.www.service.UserService;
import com.www.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  16:19 星期四
 * @version 11.0.9
 * @since 16
 */
class UserServiceImplTest {
    UserService userService = new UserServiceImpl();
    
    @Test
    void registerUser() {
        userService.registerUser(new User("w123","w123","W123.@qq.com"));
        userService.registerUser(new User("l123","l123","l123.@qq.com"));
    }
    
    @Test
    void login() {
        System.out.println(userService.login(new User("w123", "w1235")));
    }
    
    @Test
    void existsUsername() {
        System.out.println(userService.existsUsername(new User("W123")));
    }
}