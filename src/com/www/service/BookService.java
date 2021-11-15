package com.www.service;

import com.www.pojo.Book;
import com.www.pojo.Page;

import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  11:24 星期五
 * @version 11.0.9
 * @since 16
 */
public interface BookService {
    /**
     * 添加图书
     *
     * @param book 实体类
     */
    void addBook(Book book);
    
    /**
     * 根据 图书 Id 删除图书
     *
     * @param id 图书 ID
     */
    void deleteBookById(Book id);
    
    /**
     * 修改图书信息
     *
     * @param book 实体类
     */
    void updateBook(Book book);
    
    /**
     * 根据图书 id  查询图书信息
     *
     * @param id 图书 ID
     * @return
     */
    Book queryBookById(Book id);
    
    /**
     * 查询所有 图书信息
     */
    List<Book> queryBooks();
    
    /**
     * 分页
     */
    Page<Book> page(int pageNo, int pageSize);
    
    /**
     * 价格查询分页
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
