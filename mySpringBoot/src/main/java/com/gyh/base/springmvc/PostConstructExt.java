package com.gyh.base.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器启动后调用的方法
 * 也可在方法上使用@PostConstruct注解
 *
 * @author guoyanhong
 * @date 2018/9/12 17:31
 */
@Component
public class PostConstructExt implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        requestMappingHandlerAdapterExt();
    }


    /**
     * 添加自定义 responseBody 处理器，并放在第一位
     * 在afterPropertiesSet中调用或者使用@PostConstruct
     */
    public void requestMappingHandlerAdapterExt() {
        try {
            System.out.println("add RequestResponseBodyMethodProcessorExt to HandlerMethodReturnValueHandlers...");
            // 消息转换器，在此处可以对转换器进行自定义配置
            List<HttpMessageConverter<?>> httpMessageConverters = requestMappingHandlerAdapter.getMessageConverters();
            // 添加自定义 responseBody 处理器，并放在第一位
            RequestResponseBodyMethodProcessorExt processorExt = new RequestResponseBodyMethodProcessorExt(httpMessageConverters);
            List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
            List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(handlerMethodReturnValueHandlers.size() + 1);
            newReturnValueHandlers.add(processorExt);
            newReturnValueHandlers.addAll(handlerMethodReturnValueHandlers);
            requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
        } catch (Exception e) {
            logger.error("add RequestResponseBodyMethodProcessorExt to HandlerMethodReturnValueHandlers has an ex:", e);
        }
    }
}
