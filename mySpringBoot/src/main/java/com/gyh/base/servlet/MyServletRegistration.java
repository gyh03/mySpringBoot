package com.gyh.base.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoyanhong
 * @date 2018/10/31 18:00
 */
@Configuration
public class MyServletRegistration {

    @Bean
    public ServletRegistrationBean myServlet1(){
        ServletRegistrationBean servlet = new ServletRegistrationBean(new MyServlet1(),"/servlet1");
        // servlet 加载顺序，序号小的先加载
        servlet.setLoadOnStartup(5);
        return servlet;
    }
    @Bean
    public ServletRegistrationBean myServlet2(){
        ServletRegistrationBean servlet = new ServletRegistrationBean(new MyServlet2(),"/servlet2");
        // servlet 加载顺序，序号小的先加载
        servlet.setLoadOnStartup(6);
        return servlet;
    }
}
