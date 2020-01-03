package com.gyh.base.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoyanhong
 * @date 2018/9/20 15:58
 */
//@Configuration
public class MyListenerRegistration {

    @Bean
    public ServletListenerRegistrationBean myHttpSessionListener(){
        MyHttpSessionListener myHttpSessionListener = new MyHttpSessionListener();
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(myHttpSessionListener);
        listenerRegistrationBean.setOrder(1);
        return listenerRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean myServletContextListener(){
        MyServletContextListener yServletContextListener = new MyServletContextListener();
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(yServletContextListener);
        listenerRegistrationBean.setOrder(2);
        return listenerRegistrationBean;
    }
}
