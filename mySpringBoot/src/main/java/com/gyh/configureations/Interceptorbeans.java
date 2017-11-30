package com.gyh.configureations;

import com.gyh.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 相当于之前在xml中配置bean
 * 可以在类中使用注解向容器中添加 拦截器配置，上传控件，自定义格式化类 等
 */
@Configuration
public class Interceptorbeans extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
