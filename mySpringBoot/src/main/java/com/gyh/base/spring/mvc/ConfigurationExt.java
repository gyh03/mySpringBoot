package com.gyh.base.spring.mvc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @Configuration 相当于一个 Bean 配置文件
 * @author guoyanhong
 * @date 2019/12/16 20:42
 */
@Configuration
public class ConfigurationExt {


    /**
     * 注入一个 sessionLocaleResolver 到 DispatcherServlet 中，
     * 代替默认配置的 AcceptHeaderLocaleResolver
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    /**
     * 配置 ResourceBundleMessageSource ，也可以在配置文件中配置 spring.message.basename=i18n/message
     * @return
     */
//    @Bean
//    public MessageSource messageSource(){
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("i18n/message");
//        resourceBundleMessageSource.setDefaultEncoding("utf-8");
//        return resourceBundleMessageSource;
//    }
}
