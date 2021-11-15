package com.www.test;

import com.www.pojo.CarItem;
import com.www.pojo.Cart;
import com.www.pojo.User;
import com.www.service.OrderService;
import com.www.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  10:08 星期一
 * @version 11.0.9
 * @since 16
 */
class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    
    @Test
    void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(1, "www", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CarItem(2, "lll", 5, new BigDecimal(300), new BigDecimal(1500)));
    
        System.out.println("订单号是："+orderService.createOrder(cart, new User(1)));
        
        
    }
}