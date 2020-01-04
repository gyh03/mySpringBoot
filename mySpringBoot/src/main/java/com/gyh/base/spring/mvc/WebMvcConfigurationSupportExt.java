package com.gyh.base.spring.mvc;

import com.gyh.base.interceptor.LoginInterceptor;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展 WebMvc 容器
 * <p>
 * 添加 自定义插件 或 非默认容器插件，如：
 * ArgumentResolver
 * HandlerMethodReturnValueHandler
 * ExceptionResolver
 * ConversionService
 * HandlerInterceptor
 * ResourceHandler
 * ViewController
 * ...
 *
 * @author guoyanhong
 * @date 2018/9/19 18:03
 */
@Component
public class WebMvcConfigurationSupportExt extends WebMvcConfigurationSupport {

    /**
     * 添加 HandlerInterceptor
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LocaleChangeInterceptor());
        super.addInterceptors(registry);
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
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        super.addFormatters(registry);
    }

}
