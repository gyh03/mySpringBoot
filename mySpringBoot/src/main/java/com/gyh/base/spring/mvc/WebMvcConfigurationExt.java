package com.gyh.base.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * SpringMvc Bean 配置类
 * <p>
 * 相当于一个 Bean 配置文件，
 * 可以使用 @Bean注解 向容器中注入 Bean
 *
 * @author guoyanhong
 * @date 2019/12/16 20:42
 */
@Configuration
public class WebMvcConfigurationExt {


    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * 注入一个 sessionLocaleResolver 到 DispatcherServlet 中，
     * 代替默认配置的 AcceptHeaderLocaleResolver
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    /**
     * 配置 ResourceBundleMessageSource ，也可以在配置文件中配置 spring.message.basename=i18n/message
     *
     * @return
     */
//    @Bean
//    public MessageSource messageSource(){
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("i18n/message");
//        resourceBundleMessageSource.setDefaultEncoding("utf-8");
//        return resourceBundleMessageSource;
//    }

    @PostConstruct
    public void addFristReturnValueHandler() {
        // 若想将自定义的 returnValueHandler 放到列表的第一位（即匹配时优先选择），
        // 那么需要在RequestMappingHandlerAdapter实例化后，把该returnValueHandler加入到returnValueHandlers列表中
        ResponseBodyReturnValueHandlerExt processorExt = new ResponseBodyReturnValueHandlerExt(requestMappingHandlerAdapter.getMessageConverters());
        List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(handlerMethodReturnValueHandlers.size() + 1);
        newReturnValueHandlers.add(processorExt);
        newReturnValueHandlers.addAll(handlerMethodReturnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }
}
