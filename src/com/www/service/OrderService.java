package com.www.service;

import com.www.pojo.Cart;
import com.www.pojo.User;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/25  9:46 星期一
 * @version 11.0.9
 * @since 16
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param cart 购物车实体类
     * @param id   用户编号
     * @return 订单号
     */
    String createOrder(Cart cart, User id);
    
}
