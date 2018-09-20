package com.gyh.base.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册 Filter 并设置顺序
 *
 * @author guoyanhong
 * @date 2018/9/20 15:11
 */
@Configuration
public class MyFilterRegistration {

    @Bean
    public FilterRegistrationBean myFilter() {
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter2() {
        MyFilter2 myFilter = new MyFilter2();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.addUrlPatterns("/user/*");
        filterRegistrationBean.setOrder(3);
        return filterRegistrationBean;
    }
}
