package com.gyh.base.spring.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加扩展 ResponseBody 处理器，并放在第一位
 * 容器启动后调用的方法
 * 也可在方法上使用@PostConstruct注解
 *
 * @author guoyanhong
 * @date 2018/9/12 17:31
 */
@Component
public class RequestMappingHandlerAdapterExt implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    private MessageSource messageSource;


    @Override
    public void afterPropertiesSet() throws Exception {
        addResponseBodyReturnValueHandlerExt();
    }


    /**
     * 添加扩展 ResponseBody 处理器，并放在第一位
     * 在afterPropertiesSet中调用或者使用@PostConstruct
     */
    public void addResponseBodyReturnValueHandlerExt() {
        try {
            System.out.println("add RequestResponseBodyMethodProcessorExt to HandlerMethodReturnValueHandlers...");
            // 消息转换器，在此处可以对转换器进行自定义配置
            List<HttpMessageConverter<?>> httpMessageConverters = requestMappingHandlerAdapter.getMessageConverters();
            // 添加自定义 responseBody 处理器，并放在第一位
            ResponseBodyReturnValueHandlerExt processorExt = new ResponseBodyReturnValueHandlerExt(httpMessageConverters);
            List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
            List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(handlerMethodReturnValueHandlers.size() + 1);
            newReturnValueHandlers.add(processorExt);
            newReturnValueHandlers.addAll(handlerMethodReturnValueHandlers);
            requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
        } catch (Exception e) {
            logger.error("add RequestResponseBodyMethodProcessorExt to HandlerMethodReturnValueHandlers has an ex:", e);
        }
    }

    class ResponseBodyReturnValueHandlerExt extends RequestResponseBodyMethodProcessor {

        public ResponseBodyReturnValueHandlerExt(List<HttpMessageConverter<?>> converters) {
            super(converters);
        }

        @Override
        public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
                throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
            System.out.println("自定义ResponseBody处理器，在此处可以针对ResponseBody进行再封装...");
            // 比如此处处理国际化
            String code = "demoMsg";
            String localeValue = messageSource.getMessage(code,null, LocaleContextHolder.getLocale());
            System.out.println("国际化：" + code + " = " + localeValue);
            super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }
}



