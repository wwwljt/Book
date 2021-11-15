package com.www.web;

import com.www.pojo.Book;
import com.www.pojo.Page;
import com.www.service.BookService;
import com.www.service.impl.BookServiceImpl;
import com.www.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/23  17:00 星期六
 * @version 11.0.9
 * @since 16
 */
public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    
    
    /**
     * 处理分页功能
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            System.out.println("经过了前台的 ClientBookServlet 程序");
            //1、获取请求参数 pageNo 和 pageSize
            int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            //2、调用BookService.page(pageNo,pageSize) :page 对象
            Page<Book> bookPage = bookService.page(pageNo, pageSize);
            
            bookPage.setUrl("client/clientBookServlet?action=page");
            
            //3、保存 Page 对象到 request 域中
            req.setAttribute("bookPage", bookPage);
            //4、重定向到图书列表管理页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
            
        }
    }
    
    
    /**
     *
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            
            System.out.println("经过了前台的 ClientBookServlet 程序");
            //1、获取请求参数 pageNo 和 pageSize
            int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            int min = WebUtils.parseInt(req.getParameter("min"), 0);
            int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
            //2、调用BookService.page(pageNo,pageSize) :page 对象
            Page<Book> bookPage = bookService.pageByPrice(pageNo, pageSize, min, max);
            
            
            StringBuilder stringBuilder = new StringBuilder("client/clientBookServlet?action=pageByPrice");
            //如果有你最小价格的参数，追加到 分页条的地址参数中
            if (req.getParameter("min") != null) {
                stringBuilder.append("&min=").append(req.getParameter("min"));
            }
            //如果有你最大价格的参数，追加到 分页条的地址参数中
            if (req.getParameter("max") != null) {
                stringBuilder.append("&max=").append(req.getParameter("max"));
            }
            
            bookPage.setUrl(stringBuilder.toString());
            //3、保存 Page 对象到 request 域中
            req.setAttribute("bookPage", bookPage);
            //4、重定向到图书列表管理页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
            
        }
    }
}
