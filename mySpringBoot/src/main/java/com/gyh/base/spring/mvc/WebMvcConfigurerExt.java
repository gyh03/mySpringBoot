package com.gyh.base.spring.mvc;

import com.gyh.base.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;

/**
 * 相当于之前在xml中配置bean
 * 可以在类中使用注解向容器中添加 拦截器配置，上传控件，自定义格式化类 等
 * @author guoyanhong
 * @date 2018/9/19 18:03
 */
@Configuration
public class WebMvcConfigurerExt extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LocaleChangeInterceptor());
        super.addInterceptors(registry);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // 此处可以添加自定义ReturnValueHandler
        super.addReturnValueHandlers(returnValueHandlers);
    }
}
