package com.www.dao.impl;

import com.www.dao.OrderDao;
import com.www.pojo.Order;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/24  23:25 星期日
 * @version 11.0.9
 * @since 16
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     * 订单保存
     *
     * @return 返回 -1 保存失败，反之成功
     */
    @Override
    public int saveOrder(Order order) {
        if (order != null) {
            System.out.println("OrderDaoImpl 程序 在【" + Thread.currentThread().getName() + "】中");
            String sql = "insert into t_order(order_id, create_time, price, status, user_id) values(?,?,?,?,?)";
            return this.update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        } else {
            return -1;
        }
    }
}
