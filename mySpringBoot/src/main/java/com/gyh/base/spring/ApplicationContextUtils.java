package com.gyh.base.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 容器 工具类，使用此类获取容器中的 Bean
 *
 * @author guoyanhong
 * @date 2018/9/18 11:32
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    /**
     * Spring 容器
     */
    public static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.appContext = applicationContext;
    }

    /**
     * 根据 beanName 获取Bean
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        return (T) appContext.getBean(name);
    }

    /**
     * 根据 BeanType 获取Bean
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return appContext.getBean(requiredType);
    }
}
