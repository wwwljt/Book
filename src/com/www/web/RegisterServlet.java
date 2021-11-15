package com.www.web;


import com.www.pojo.User;
import com.www.service.UserService;
import com.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/21  16:26 星期四
 * @version 11.0.9
 * @since 16
 */
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求的参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String code = req.getParameter("code");
            
            
            //2、检查验证码是否正确   ==>写死 ，要求验证码为 abcde
            if ("abcde".equalsIgnoreCase(code)) {
                //正确
                //3、检查用户名是否可用
                if (userService.existsUsername(new User(username))) {
                    //把回显信息，保存到 request 域中
                    req.setAttribute("msg", "用户名已存在！");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    
                    //不可用, 跳回注册页面
                    System.out.println("用户名" + username + "已存在");
                    req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
                } else {
                    //可用  ;调用service 保存到数据库
                    userService.registerUser(new User(username, password, email));
                    //跳转到注册成功页面
                    req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req, resp);
                }
            } else {
                //把回显信息，保存到 request 域中
                req.setAttribute("msg", "验证码错误！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                
                System.out.println("验证码[" + code + "]错误");
                //不正确  ； 跳回注册页面
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
            }
        }
    }
}
