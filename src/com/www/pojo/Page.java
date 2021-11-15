package com.www.pojo;

import java.util.List;

/**
 * @param <T> 是具体的模块的javaBean类
 * @author 温伟伟
 * <br> TODO(Page是分页的模型对象)
 * <p> 创建时间：2021/10/22  16:58 星期五
 * @version 11.0.9
 * @since 16
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    
    //当前页码
    private Integer pageNo;
    
    //总页码
    private Integer pageTotal;
    
    //当前页显示的数量
    private Integer pageSize = PAGE_SIZE;
    
    //总记录
    private Integer pageTotalCount;
    
    //当前页数据
    private List<T> items;
    
    // 分页条的请求地址
    private String url;
    
    public Integer getPageNo() {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo) {
     /*    数据边界的有效检查 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
    
        this.pageNo = pageNo;
    }
    
    public Integer getPageTotal() {
        return pageTotal;
    }
    
    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getPageTotalCount() {
        return pageTotalCount;
    }
    
    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public void setItems(List<T> items) {
        this.items = items;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
