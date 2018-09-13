package com.gyh.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 操作对象日志
 */
//注解用于什么地方
@Target(ElementType.METHOD)
//什么时候使用该注解
@Retention(RetentionPolicy.RUNTIME)
public @interface OpeLogInfo {
    String node() default "";//操作对象
}
