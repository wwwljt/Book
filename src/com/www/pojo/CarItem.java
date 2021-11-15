package com.www.pojo;

import java.math.BigDecimal;

/**
 * @author 温伟伟
 * <br> TODO(购物车的商品项)
 * <p> 创建时间：2021/10/22  16:40 星期五
 * @version 11.0.9
 * @since 16
 */
public class CarItem {
    //属性
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    
    
    //get、set方法
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    /**
     * 有参构造
     */
    public CarItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    
    public CarItem(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }
    
    public CarItem(Integer id) {
        this.id = id;
    }
    
    /**
     * 无参构造
     */
    public CarItem() {
    }
    
    @Override
    public String toString() {
        return "CarItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
