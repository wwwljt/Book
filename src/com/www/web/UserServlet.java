package com.www.web;

import com.google.gson.Gson;
import com.www.pojo.User;
import com.www.service.UserService;
import com.www.service.impl.UserServiceImpl;
import com.www.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  7:55 星期五
 * @version 11.0.9
 * @since 16
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    
    /**
     * 处理登录请求
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求的参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            
            //调用 userService.login()  登录处理业务
            User loginUser = userService.login(new User(username, password));
            if (loginUser == null) {
                //把错误信息 和回显的表单项信息，保存到 request 域中
                req.setAttribute("msg", "用户密码错误！");
                req.setAttribute("username", username);
                // 如果等于 null 说明登录 失败！
                System.out.println(username + "登录失败！");
                //跳转到登录页面
                req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            } else {
                
                //不等于 null ，说明登录成功
                System.out.println(username + "登录成功！");
                //保存用户登录的信息到Session中
                req.getSession().setAttribute("user", loginUser);
                //跳转到登录成功页面
                req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
            }
        }
    }
    
    /**
     * 注销
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            //1、销毁Session 中用户登录的信息（或销毁 Session)
            req.getSession().invalidate();
            //2、重定向到首页（ 或登录页面）
            resp.sendRedirect(req.getContextPath());
        }
    }
    
    
    /**
     * 处理注册的请求
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            //获取session 中的验证码
            String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            System.out.println("验证码：" + token);
            //删除Session中的验证码
            req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            
            //1、获取请求的参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String code = req.getParameter("code");
            
            //            把Map中的值 注入到 JavaBean 属性中
            WebUtils.copyParamToBean(req.getParameterMap(), new User());
            
            Map<String, String[]> parameterMap = req.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                System.out.println(entry.getKey() + "  ==  " + Arrays.asList(entry.getValue()));
            }
            
            //2、检查验证码是否正确   ==>写死 ，要求验证码为 abcde
            if (token != null && token.equalsIgnoreCase(code)) {
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
    
    
    /**
     * 处理注册的请求
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            //获取请求的参数username
            String username = req.getParameter("username");
            System.out.println(username);
            //调用 userService .existsUsername()
            boolean existsUsername = userService.existsUsername(new User(username));
            
            //把返回的结果封装为 map 对象
            Map<String, Object> resultMap = new Hashtable<>();
            resultMap.put("existsUsername", existsUsername);
            
            //创建Gson 实例化
            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            
            //通过响应的 字符输出流 输出 json
            resp.getWriter().write(json);
            
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
