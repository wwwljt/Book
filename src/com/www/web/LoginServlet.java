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
 * <p> 创建时间：2021/10/21  19:03 星期四
 * @version 11.0.9
 * @since 16
 */
public class LoginServlet extends HttpServlet {
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
            
            //调用 userService.login()  登录处理业务
            if (userService.login(new User(username, password)) == null) {
                //把错误信息 和回显的表单项信息，保存到 request 域中
                req.setAttribute("msg","用户密码错误！");
                req.setAttribute("username",username);
                // 如果等于 null 说明登录 失败！
                System.out.println(username + "登录失败！");
                //跳转到登录页面
                req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            } else {
                //不等于 null ，说明登录成功
                System.out.println(username + "登录成功！");
                //跳转到登录成功页面
                req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
            }
        }
    }
}
