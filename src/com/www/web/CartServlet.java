package com.www.web;

import com.google.gson.Gson;
import com.www.pojo.Book;
import com.www.pojo.CarItem;
import com.www.pojo.Cart;
import com.www.service.BookService;
import com.www.service.impl.BookServiceImpl;
import com.www.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/24  10:15 星期日
 * @version 11.0.9
 * @since 16
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    
    /**
     * 修改商品数量
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //获取请求的参数 商品编号 ，商品数量
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            int count = WebUtils.parseInt(req.getParameter("count"), 1);
            
            
            //获取 Cart 购物车对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            
            if (cart != null) {
                //修改商品数量
                cart.updateCount(new CarItem(id, count));
                
                //重定向回原来商品的页面
                resp.sendRedirect(req.getHeader("Referer"));
            }
            
            
        }
    }
    
    /**
     * 清空购物车
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //获取购物车对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            
            if (cart != null) {
                //清空购物车
                cart.clear();
                //重定向回原来商品的页面
                resp.sendRedirect(req.getHeader("Referer"));
            }
            
            
        }
    }
    
    
    /**
     * 删除商品项
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //获取请求的参数，商品编号
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            
            //获取购物车对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            
            if (cart != null) {
                //删除了购物车商品项
                cart.deleteItem(new CarItem(id));
                //重定向回原来商品的页面
                resp.sendRedirect(req.getHeader("Referer"));
            }
            
            
        }
    }
    
    /**
     * 加入购物车
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            System.out.println("加入购物 车！");
            System.out.println("商品编号：" + req.getParameter("id"));
            
            //获取请求的参数，商品编号
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            //调用 bookService.queryBookById(id):  book 得到图书信息
            Book book = bookService.queryBookById(new Book(id));
            //把图书信息，转换成 CartItem商品项
            CarItem carItem = new CarItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
            
            //调用Cart.addItem(CartItem)添加商品
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }
            cart.addItem(carItem);
            
            System.out.println("请求Referer的值：" + req.getHeader("Referer"));
            System.out.println(cart);
            
            //           最后一个添加的商品
            req.getSession().setAttribute("lastName", carItem.getName());
            
            //重定向回原来商品的页面
            resp.sendRedirect(req.getHeader("Referer"));
        }*/
    }
    
    /**
     * 加入购物车
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
    
            System.out.println("加入购物 车！");
            System.out.println("商品编号：" + req.getParameter("id"));
    
            //获取请求的参数，商品编号
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            //调用 bookService.queryBookById(id):  book 得到图书信息
            Book book = bookService.queryBookById(new Book(id));
            //把图书信息，转换成 CartItem商品项
            CarItem carItem = new CarItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
    
            //调用Cart.addItem(CartItem)添加商品
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            System.out.println(cart);
            if (cart == null) {
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }
            cart.addItem(carItem);
            
//            System.out.println("请求Referer的值：" + req.getHeader("Referer"));
            System.out.println(cart);
    
            //           最后一个添加的商品
            req.getSession().setAttribute("lastName", carItem.getName());
            
            
            //返回 购物车总的商品数量 和最后一个 添加的商品名称
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("totalCount", cart.getTotalCount());
            resultMap.put("lastName", carItem.getName());
            
            
            //创建 Gson 实例
            Gson gson = new Gson();
            
            String resultMapJsonString = gson.toJson(resultMap);
            System.out.println(resultMapJsonString);
            resp.getWriter().write(resultMapJsonString);
            
            
            
            
        }
    }
}
