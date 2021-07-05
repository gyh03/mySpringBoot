package com.gyh.test;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author guoyanhong5
 * @date 2021-03-10 19:23
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface CustomAnnotation {
    /**
     * 参数
     *
     * @return
     */
    String params();
}
