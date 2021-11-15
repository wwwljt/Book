package com.www.filter;

import com.www.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/27  9:42 星期三
 * @version 11.0.9
 * @since 16
 */

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest != null && filterChain != null) {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
                JdbcUtils.commitAndClose();//提交事务
            } catch (Exception e) {
                JdbcUtils.rollbackAndClose();//回滚事务
                e.printStackTrace();
                throw new RuntimeException(e);//把异常抛给 tomcat 管理展示友好的错误页面
            }
            
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
