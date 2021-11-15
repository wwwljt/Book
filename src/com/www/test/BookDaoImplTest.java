package com.www.test;

import com.www.dao.BookDao;
import com.www.dao.impl.BookDaoImpl;
import com.www.pojo.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  11:08 星期五
 * @version 11.0.9
 * @since 16
 */
class BookDaoImplTest {
    BookDao bookDao = new BookDaoImpl();
    
    @Test
    void addBook() {
        System.out.println(bookDao.addBook(new Book("活着", "余华", new BigDecimal(45), 10, 20)));
    }
    
    @Test
    void deleteBookById() {
        System.out.println(bookDao.deleteBookById(new Book(3)));
    }
    
    @Test
    void updateBook() {
        System.out.println(bookDao.updateBook(new Book(21, "活着", "www", new BigDecimal(45), 10, 20, null)));
    }
    
    @Test
    void queryBookById() {
        System.out.println(bookDao.queryBookById(new Book(21)));
    }
    
    @Test
    void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    @Test
    void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    
    @Test
    void queryForPageItems() {
        for (Book queryForPageItem : bookDao.queryForPageItems(0, 6)) {
            System.out.println(queryForPageItem);
        }
    }
    
    @Test
    void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }
    
    @Test
    void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, 5, 10, 50)) {
            System.out.println(book);
            
        }
    }
}