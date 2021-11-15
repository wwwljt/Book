package com.www.service.impl;

import com.www.dao.BookDao;
import com.www.dao.OrderDao;
import com.www.dao.OrderItemDao;
import com.www.dao.impl.BookDaoImpl;
import com.www.dao.impl.OrderDaoImpl;
import com.www.dao.impl.OrderItemDaoImpl;
import com.www.pojo.*;
import com.www.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  9:49 星期一
 * @version 11.0.9
 * @since 16
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    
    /**
     * 创建订单
     *
     * @param cart 购物车实体类
     * @param id   用户编号
     * @return 订单号
     */
    @Override
    public String createOrder(Cart cart, User id) {
        if (cart != null && id != null) {
            System.out.println("OrderServiceImpl 程序 在【" + Thread.currentThread().getName() + "】中");
            
            //订单号 ==== 唯一性
            String orderId = System.currentTimeMillis() + "" + id.getId();
            
            //创建一个订单对象
            Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, id.getId());
            
            //保存订单
            int i = orderDao.saveOrder(order);
            
            //验证是否成功 -1，失败，其他成功
            System.out.println(i);
            
            //遍历购物车中的每个商品项转换成订单项保存到数据库
            for (Map.Entry<Integer, CarItem> entry : cart.getItemMap().entrySet()) {
                //获取每一个购物车中的商品项
                CarItem carItem = entry.getValue();
                
                //转换为每一个订单项
                OrderItem orderItem = new OrderItem(null, carItem.getName(), carItem.getCount(), carItem.getPrice(), carItem.getTotalPrice(), orderId);
                //保存订单项
                int i1 = orderItemDao.saveOrderItem(orderItem);
                
                //更新库存和销量
                Book book = bookDao.queryBookById(new Book(carItem.getId()));
                book.setSales(book.getSales() + carItem.getCount());
                book.setStock(book.getStock() - carItem.getCount());
                
                int i2 = bookDao.updateBook(book);
                System.out.println("更新库存和销量:" + i2);
                //验证是否成功 -1，失败，其他成功
                System.out.println(i1);
            }
            
            //清空购物车
            cart.clear();
            return orderId;
        } else {
            return null;
        }
    }
}

