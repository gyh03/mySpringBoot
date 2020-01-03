package com.gyh.base.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

/**
 * 自定义 ResponseBodyReturnValueHandler， 即 ResponseBody扩展处理器
 *
 * @author guoyanhong
 * @date 2020/1/3 12:33
 */
public class ResponseBodyReturnValueHandlerExt extends RequestResponseBodyMethodProcessor {

    // 国际化资源读取器
    @Autowired
    private MessageSource messageSource;

    public ResponseBodyReturnValueHandlerExt(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        System.out.println("自定义ResponseBody处理器，在此处可以针对ResponseBody进行再封装...");
        // 比如此处处理国际化
        String code = "demoMsg";
        String localeValue = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        System.out.println("国际化：" + code + " = " + localeValue);
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}