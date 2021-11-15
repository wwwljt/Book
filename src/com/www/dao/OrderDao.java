package com.www.dao;

import com.www.pojo.Order;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/24  23:21 星期日
 * @version 11.0.9
 * @since 16
 */
public interface OrderDao {
    /**
     * 订单保存
     *
     * @return 返回 -1 保存失败，反之成功
     */
    int saveOrder(Order order);
}
