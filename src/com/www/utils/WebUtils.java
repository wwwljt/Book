package com.www.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 温伟伟
 * <br> TODO()
 * <p> 创建时间：2021/10/22  9:15 星期五
 * @version 11.0.9
 * @since 16
 */
public class WebUtils {
    /**
     * 把Map中的值 注入到 JavaBean 属性中
     */
    public static <T> T copyParamToBean(Map mapValue, T bean) {
        
        if (mapValue != null && bean != null) {
            try {
                System.out.println("注入之前：" + bean);
                
                /* 把所有的请求都注入到 bean 对象中  */
                BeanUtils.populate(bean, mapValue);
                
                System.out.println("注入之后" + bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            
            return bean;
        } else {
            return null;
        }
    }
    
    /**
     * 将字符串转换成 int类型数据
     *
     *
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
