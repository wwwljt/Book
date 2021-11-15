package com.www.test;

import com.www.dao.OrderItemDao;
import com.www.dao.impl.OrderItemDaoImpl;
import com.www.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  9:34 星期一
 * @version 11.0.9
 * @since 16
 */
class OrderItemDaoImplTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    
    @Test
    void saveOrderItem() {
        System.out.println(orderItemDao.saveOrderItem(new OrderItem(null, "哇哇哇哇", 1, new BigDecimal(100), new BigDecimal(100), "1234567890")));
        System.out.println(orderItemDao.saveOrderItem(new OrderItem(null, "野果", 1, new BigDecimal(200), new BigDecimal(200), "1234567890")));
        System.out.println(orderItemDao.saveOrderItem(new OrderItem(null, "世界", 1, new BigDecimal(200), new BigDecimal(200), "1234567890")));
    }
}