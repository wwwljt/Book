package com.www.web;

import com.www.pojo.Cart;
import com.www.pojo.User;
import com.www.service.OrderService;
import com.www.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  10:13 星期一
 * @version 11.0.9
 * @since 16
 */
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();
    
    /**
     * 生成订单
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //先获取 Cart 购物车对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            
            //获取 UserId
            User loginUser = (User) req.getSession().getAttribute("user");
            
            if (loginUser == null) {
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
                return;
            }
            System.out.println("OrderServlet 程序 在【" + Thread.currentThread().getName() + "】中");
            Integer userId = loginUser.getId();
            //调用 orderService.createOrder(Cart cart,User id){} 生成订单
            String orderId = orderService.createOrder(cart, new User(userId));
            
            
            //            req.setAttribute("orderId", orderId);
            
            //请求转发到/pages/cart/checkout.jsp
            //            req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
            req.getSession().setAttribute("orderId", orderId);
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
            
            
        }
    }
}
