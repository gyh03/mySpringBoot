package com.gyh.base.spring.mvc;

import com.gyh.base.interceptor.LoginInterceptor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展 WebMvc 容器
 * <p>
 * 添加 自定义插件 或 非默认容器组件，如：
 * ArgumentResolver
 * HandlerMethodReturnValueHandler
 * ExceptionResolver
 * ConversionService
 * HandlerInterceptor
 * ResourceHandler
 * ViewController
 * ...
 *
 * springboot 启动时，加载spring.factories文件中指定的类，
 * 其中有org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,即webmvc自动配置类，会自动加载一些MVC会用到的组件，
 * 这是实现WebMvcConfigurer接口，可以扩展组件（自定义组件或其他想要使用的springMvc组件）。
 *
 * 此处但是不可以继承WebMvcConfigurationSupport类，因为WebMvcAutoConfiguration类上使用了@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)条件，
 * 若此处继承WebMvcConfigurationSupport类并注入到spring容器中，会导致容器不加载WebMvcAutoConfiguration类，从而导致WebMVC一些默认的组件不能被加载到容器中，会而造成一些问题，如：
 * WebMvcAutoConfiguration类中的ContentNegotiatingViewResolver(一种视图解析器)不能被加载，若controller中返回了"redirect:xxxUrl"，而其他视图解析器不能正确解析重定向地址，则会报错。
 *
 * @author guoyanhong
 * @date 2018/9/19 18:03
 */
@Component
public class WebMvcConfigurerExt implements WebMvcConfigurer {


    /**
     * 添加 HandlerInterceptor
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    /**
     * 添加 ReturnValueHandler
     *
     * @param returnValueHandlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // 添加自定义 responseBody 处理器
//        returnValueHandlers.add(new ResponseBodyReturnValueHandlerExt(getMessageConverters()));
    }

    /**
     * 添加 Converter
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
    }

}
