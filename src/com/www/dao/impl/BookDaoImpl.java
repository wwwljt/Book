package com.www.dao.impl;

import com.www.dao.BookDao;
import com.www.pojo.Book;

import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  10:52 星期五
 * @version 11.0.9
 * @since 16
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 添加图书
     *
     * @param book 实体类
     */
    @Override
    public int addBook(Book book) {
        if (book != null) {
            String sql = "insert into t_book(`name`, `author`, `price`, `sales`, `stock`, `img_path`) values(?,?,?,?,?,?)";
            return this.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImPath());
        } else {
            return -1;
        }
    }
    
    /**
     * 根据 图书 Id 删除图书
     *
     * @param id 图书 ID
     */
    @Override
    public int deleteBookById(Book id) {
        if (id != null) {
            String sql = "delete from t_book  where id=?";
            return this.update(sql, id.getId());
            
        } else {
            return -1;
        }
    }
    
    /**
     * 修改图书信息
     *
     * @param book 实体类
     */
    @Override
    public int updateBook(Book book) {
        if (book != null) {
            System.out.println("BookDaoImpl 程序 在【" + Thread.currentThread().getName() + "】中");
            
            String sql = "update  t_book set `name`=?, `author`=?, `price`=?, `sales`=?, `stock`=?, `img_path`=?  where id=?";
            return this.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImPath(), book.getId());
            
        } else {
            return -1;
        }
    }
    
    /**
     * 根据图书 id  查询图书信息
     *
     * @param id 图书 ID
     */
    @Override
    public Book queryBookById(Book id) {
        if (id != null) {
            String sql = "select id, name, author, price, sales, stock, img_path imgPath  from t_book where id =?";
            return this.queryForOne(Book.class, sql, id.getId());
            
        } else {
            return null;
        }
    }
    
    /**
     * 查询所有 图书信息
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book";
        return this.queryForList(Book.class, sql);
    }
    
    /**
     * 求总记录数
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }
    
    /**
     * 求当前页数据
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath  from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }
    
    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ? ";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }
    
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath  from t_book  where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
