package com.www.dao;

import com.www.pojo.Book;

import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  10:48 星期五
 * @version 11.0.9
 * @since 16
 */
public interface BookDao {
    /**
     * 添加图书
     */
    int addBook(Book book);
    
    /**
     * 根据 图书 Id 删除图书
     */
    int deleteBookById(Book id);
    
    /**
     * 修改图书信息
     */
    int updateBook(Book book);
    
    /**
     * 根据图书 id  查询图书信息
     */
    Book queryBookById(Book id);
    
    /**
     * 查询所有 图书信息
     */
    List<Book> queryBooks();
    
    /**
     * 求总记录数
     */
    Integer queryForPageTotalCount();
    
    /**
     * 求当前页数据
     */
    List<Book> queryForPageItems(int begin, int pageSize);
    
    /**
     *根据输入的价格范围 分页查询
     *
     */
    Integer queryForPageTotalCountByPrice(int min, int max);
    
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
