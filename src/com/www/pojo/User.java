package com.www.pojo;

/**
 * @author 温伟伟
 * <br> TODO(user 实体类)
 * <p> 创建时间：2021/10/21  13:03 星期四
 * @version 11.0.9
 * @since 16
 */
public class User {
    //属性
    private Integer id;
    private String username;
    private String password;
    private String email;
    
   
    
    //get、set 方法
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 有参构造
     */
    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    /**
     * 无参构造
     */
    public User() {
    }
    
    public User(String username) {
        this.username = username;
    }
    
    public User(Integer id) {
        this.id = id;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
