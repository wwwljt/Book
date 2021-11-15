package com.www.test;

import java.lang.reflect.Method;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  8:16 星期五
 * @version 11.0.9
 * @since 16
 */
public class UserServletTest {
    
    
    public void login() {
        System.out.println("这是 login()  方法调用了");
    }
    
    
    public void regist() {
        System.out.println("这是 regist()  方法调用了");
        
    }
    
    public void addUser() {
        System.out.println("这是 addUser()  方法调用了");
    }
    
    
    public void update() {
        System.out.println("这是 update()  方法调用了");
        
    }
    
    public void updateUserPassword() {
        System.out.println("这是 updateUserPassword() 方法调用了");
        
    }
    
    
    /**
     * 方法入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        String action = "update";
        
        try {
            //获取 action 业务鉴别字符串，获取相应的业务，方法反射对象
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);
            
            //调用目标业务 方法
            declaredMethod.invoke(new UserServletTest());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}