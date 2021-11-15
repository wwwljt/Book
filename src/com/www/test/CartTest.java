package com.www.test;

import com.www.pojo.CarItem;
import com.www.pojo.Cart;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/23  22:52 星期六
 * @version 11.0.9
 * @since 16
 */
class CartTest {
    
    
    @Test
    void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(2, "lll", 5, new BigDecimal(300), new BigDecimal(1500)));
        System.out.println(cart);
    }
    
    @Test
    void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(2, "lll", 5, new BigDecimal(300), new BigDecimal(1500)));
        cart.deleteItem(new CarItem(1));
        System.out.println(cart);
    }
    
    @Test
    void clear() {
        Cart cart = new Cart();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(2, "lll", 5, new BigDecimal(300), new BigDecimal(1500)));
        cart.clear();
        System.out.println(cart);
    }
    
    @Test
    void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(2, "lll", 5, new BigDecimal(300), new BigDecimal(1500)));
        cart.deleteItem(new CarItem(1));
        cart.clear();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.updateCount(new CarItem(1,3));
        System.out.println(cart);
    }
}