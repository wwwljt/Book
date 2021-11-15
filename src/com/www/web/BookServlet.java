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
import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  11:48 星期五
 * @version 11.0.9
 * @since 16
 */
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    
    /**
     * 添加图书
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
            pageNo += 1;
            //1、获取请求参数== 封装成为Book  对象
            Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
            System.out.println(book);
            //2、调用BookService.addBook()保存图书
            bookService.addBook(book);
            //跳转到图书列表页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
        }
    }
    
    /**
     * 删除
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求参数 id  图书编程
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            
            //2、调用BookService.deleteBook()删除图书
            bookService.deleteBookById(new Book(id));
            //3、重定向到图书列表管理页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        }
    }
    
    /**
     * 修改
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求参数   封装成 book 对象
            Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
            
            //2、调用BookService.updateBook(book)修改图书
            bookService.updateBook(book);
            
            //3、重定向到图书列表管理页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        }
    }
    
    /**
     * 查询所有
     */
    protected void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、通过BookService  查询全部图书
            List<Book> books = bookService.queryBooks();
            
            //2、把全部图书保存到 Request 域中
            req.setAttribute("books", books);
            
            //3、请求转发到/pages/manager/book_manage.jsp页面
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
        }
    }
    
    /**
     * 根据 Id 查询
     */
    protected void queryBookById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求参数   图书编程
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            
            //2 调用bookService.queryBookById查询图书
            Book book = bookService.queryBookById(new Book(id));
            
            //3、把全部图书保存到 Request 域中
            req.setAttribute("book", book);
            
            //4、请求转发到/pages/manager/book_manage.jsp页面
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
        }
    }
    
    
    /**
     * 处理分页功能
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req != null && resp != null) {
            resp.setContentType("text/html;charset=utf-8");//设置响应类型和字符集
            req.setCharacterEncoding("utf-8");//设置编码格式
            //1、获取请求参数 pageNo 和 pageSize
            int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            //2、调用BookService.page(pageNo,pageSize) :page 对象
            Page<Book> bookPage = bookService.page(pageNo, pageSize);
            
            bookPage.setUrl("manager/bookServlet?action=page");
            
            //3、保存 Page 对象到 request 域中
            req.setAttribute("bookPage", bookPage);
            //4、重定向到图书列表管理页面
            //            req.getRequestDispatcher("/manager/bookServlet?action=queryAll").forward(req,resp);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
            
        }
    }
    
}
