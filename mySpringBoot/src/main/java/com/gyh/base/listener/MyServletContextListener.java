package com.gyh.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 * 监听Servlet 容器的创建与销毁
 * @author guoyanhong
 * @date 2018/9/19 18:21
 */
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener for ServletContext init");
        System.out.println(sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener for ServletContext Destroy");
    }

}