package com.www.test;

import com.www.dao.OrderDao;
import com.www.dao.impl.OrderDaoImpl;
import com.www.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  9:15 星期一
 * @version 11.0.9
 * @since 16
 */
class OrderDaoImplTest {
    OrderDao orderDao = new OrderDaoImpl();
    
    @Test
    void saveOrder() {
        System.out.println(orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(1000), 0, 1)));
    }
}