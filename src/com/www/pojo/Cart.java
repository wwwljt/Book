package com.www.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 温伟伟
 * <br> TODO(购物车对象)
 * <p> 创建时间：2021/10/22  16:51 星期五
 * @version 11.0.9
 * @since 16
 */
public class Cart {
    //    private Integer totalCount;
    //    private BigDecimal totalPrice;
    
    /*key 是商品编号
     *
     * value 是商品信息
     *
     * */
    private Map<Integer, CarItem> itemMap = new HashMap<>();
    
    /**
     * 添加商品项
     *
     * @param carItem 商品项
     */
    public void addItem(CarItem carItem) {
        if (carItem != null) {
            //先看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加，则放到到集合里
            CarItem item = itemMap.get(carItem.getId());
            if (item == null) {
                //之前没添加过
                itemMap.put(carItem.getId(), carItem);
                
            } else {
                //已添加过
                item.setCount(item.getCount() + 1);//数量累加
                item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
            }
        }
    }
    
    /**
     * 删除商品项
     *
     * @param id 商品 id
     */
    public void deleteItem(CarItem id) {
        if (id != null) {
            itemMap.remove(id.getId());
        }
    }
    
    /**
     * 清空购物车
     */
    public void clear() {
        itemMap.clear();
    }
    
    /**
     * 修改商品数量（id,count）
     */
    public void updateCount(CarItem carItem) {
        //先看购物车中是否有此商品，如果有，修改商品数量；  更新总金额
        if (carItem != null) {
            //先看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加，则放到到集合里
            CarItem item = itemMap.get(carItem.getId());
            if (item != null) {
                //已添加过
                item.setCount(carItem.getCount());//修改商品数量
                item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
            }
        }
    }
    
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CarItem> entry : itemMap.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }
    
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CarItem> entry : itemMap.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }
    
    /**
     * 有参构造
     */
    public Cart(Map<Integer, CarItem> itemMap) {
        this.itemMap = itemMap;
    }
    
    /**
     * 无参构造
     */
    public Cart() {
    }
    
    //get、set 方法
    public Map<Integer, CarItem> getItemMap() {
        return itemMap;
    }
    
    public void setItemMap(Map<Integer, CarItem> itemMap) {
        this.itemMap = itemMap;
    }
    
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", itemMap=" + itemMap +
                '}';
    }
}
