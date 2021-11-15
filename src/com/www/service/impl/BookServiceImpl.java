package com.www.service.impl;

import com.www.dao.BookDao;
import com.www.dao.impl.BookDaoImpl;
import com.www.pojo.Book;
import com.www.pojo.Page;
import com.www.service.BookService;

import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  11:36 星期五
 * @version 11.0.9
 * @since 16
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    
    /**
     * 添加图书
     *
     * @param book 实体类
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    
    /**
     * 根据 图书 Id 删除图书
     *
     * @param id 图书 ID
     */
    @Override
    public void deleteBookById(Book id) {
        bookDao.deleteBookById(id);
    }
    
    /**
     * 修改图书信息
     *
     * @param book 实体类
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    
    /**
     * 根据图书 id  查询图书信息
     *
     * @param id 图书 ID
     * @return
     */
    @Override
    public Book queryBookById(Book id) {
        return bookDao.queryBookById(id);
    }
    
    /**
     * 查询所有 图书信息
     */
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
    
    /**
     * 分页
     * @param pageNo 起始页
     * @param pageSize 每页显示的数量
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();
        
        //设置每页显示的数量
        bookPage.setPageSize(pageSize);
        
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        bookPage.setPageTotalCount(pageTotalCount);
        
        //求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        
        //设置总页码
        bookPage.setPageTotal(pageTotal);
        
        //设置当前页码
        bookPage.setPageNo(pageNo);
        
        //求当前页数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        
        //设置当前页数据
        bookPage.setItems(items);
        
        return bookPage;
    }
    
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();
    
        //设置每页显示的数量
        bookPage.setPageSize(pageSize);
    
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        bookPage.setPageTotalCount(pageTotalCount);
    
        //求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
    
        //设置总页码
        bookPage.setPageTotal(pageTotal);
    
        //设置当前页码
        bookPage.setPageNo(pageNo);
    
        //求当前页数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
    
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        
        //设置当前页数据
        bookPage.setItems(items);
        
        return bookPage;
    }
}
