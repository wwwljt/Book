package com.www.test;

import com.www.pojo.Book;
import com.www.service.BookService;
import com.www.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  11:39 星期五
 * @version 11.0.9
 * @since 16
 */
class BookServiceImplTest {
    BookService bookService = new BookServiceImpl();
    
    @Test
    void addBook() {
        bookService.addBook(new Book("死了", "余华", new BigDecimal(65), 10, 200));
    }
    
    @Test
    void deleteBookById() {
        bookService.deleteBookById(new Book(24));
    }
    
    @Test
    void updateBook() {
        bookService.updateBook(new Book(22, "活着", "余华", new BigDecimal(50), 10, 20, null));
    }
    
    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(new Book(22)));
    }
    
    @Test
    void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }
    
    @Test
    void page() {
        System.out.println(bookService.page(2, 6));
    }
    
    @Test
    void pageByPrice() {
        System.out.println(bookService.pageByPrice(2, 6,0,500));
    
    }
}