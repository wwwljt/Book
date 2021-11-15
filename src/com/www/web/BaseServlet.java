package com.www.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  8:38 星期五
 * @version 11.0.9
 * @since 16
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            String action = req.getParameter("action");
            System.out.println(action);
            try {
                //获取 action 业务鉴别字符串，获取相应的业务，方法反射对象
                Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
                System.out.println(declaredMethod);
                
                //调用目标业务 方法
                declaredMethod.invoke(this, req, resp);
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);//把异常抛给filter过滤器
            }
        }
    }
    
}
