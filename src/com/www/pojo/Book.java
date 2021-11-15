package com.www.pojo;

import java.math.BigDecimal;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  10:35 星期五
 * @version 11.0.9
 * @since 16
 */
public class Book {
    //属性
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imPath = "static/img/云温泉.jpg";
    
    //get、set 方法
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
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getSales() {
        return sales;
    }
    
    public void setSales(Integer sales) {
        this.sales = sales;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public String getImPath() {
        
        return imPath;
    }
    
    public void setImPath(String imPath) {
        //要求给定的图书封面路径不能为空
        if ("".equals(imPath)) {
            this.imPath = imPath;
        }
    }
    
    /**
     * 有参构造
     */
    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //要求给定的图书封面路径不能为空
        if ("".equals(imPath)) {
            this.imPath = imPath;
        }
    }
    
    public Book(String name, String author, BigDecimal price, Integer sales, Integer stock) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }
    
    public Book(Integer id) {
        this.id = id;
    }
    
    /**
     * 无参构造
     */
    public Book() {
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imPath='" + imPath + '\'' +
                '}';
    }
}
