package com.www.dao;

import com.www.pojo.OrderItem;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/24  23:22 星期日
 * @version 11.0.9
 * @since 16
 */
public interface OrderItemDao {
    /**
     * 订单项保存
     *
     * @return 返回 -1 ，保存失败；反之成功
     */
    int saveOrderItem(OrderItem orderItem);
    
}
