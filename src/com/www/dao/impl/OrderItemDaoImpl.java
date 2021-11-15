package com.www.dao.impl;

import com.www.dao.OrderItemDao;
import com.www.pojo.OrderItem;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/24  23:26 星期日
 * @version 11.0.9
 * @since 16
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     * 订单项保存
     *
     * @return 返回 -1 ，保存失败；反之成功
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        if (orderItem != null) {
            System.out.println("OrderItemDaoImpl 程序 在【" + Thread.currentThread().getName() + "】中");
            String sql = "insert into t_order_item(id, name, count, price, total_price, order_id) values(?,?,?,?,?,?)";
            return this.update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
            
        } else {
            return -1;
        }
    }
}
