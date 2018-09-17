package com.gyh.base.spring.mvc;

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
 * 自定义ResponseBody处理器，在此处可以针对ResponseBody进行再封装
 *
 * @author guoyanhong
 * @date 2018/9/12 16:49
 */
public class RequestResponseBodyMethodProcessorExt extends RequestResponseBodyMethodProcessor {

    public RequestResponseBodyMethodProcessorExt(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        System.out.println("自定义ResponseBody处理器，在此处可以针对ResponseBody进行再封装...");
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
