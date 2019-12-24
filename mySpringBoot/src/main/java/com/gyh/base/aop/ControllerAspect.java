package com.gyh.base.aop;

import com.gyh.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Slf4j
public class ControllerAspect {

    /**
     * 配置切入点,拦截使用@OpeLogInfo注解的，记录日志
     */
    @Pointcut("execution(* com.gyh..controller.*.*(..))")
    public void aspect() {
    }

    @Before(value = "aspect()")
    public void before() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 清除MDC数据
        MDC.remove("USER_IP");
        // 添加MDC数据，用于业务日志使用
        String userIp = IpUtils.getUserIpAddress(request);
        MDC.put("USER_IP", userIp);
        System.out.println("aop Before the method; userIp:" + userIp);
    }


}
