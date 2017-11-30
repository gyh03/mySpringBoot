package com.gyh.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session的创建与销毁
 *
 * @author gyh
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("listener for Session init");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("listener for ServletContext destroy");
    }

}