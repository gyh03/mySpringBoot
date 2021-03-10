package com.gyh.test;

import com.notIn.NotInClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CustomBean简介
 *
 * @author guoyanhong5
 * @date 2021-02-24 14:28
 */
@Component
@Configuration
public class CustomBean {
    @Autowired
//    @Qualifier(value = "sameType_A")
    private SameType sameType1;
    @Resource
    private SameType sameType2;

    private OneClass oneClass;

    /**
     * Autowired 标注的方法的参数也会被自动依赖
     *
     * @param oneBean
     */
    @Autowired
    public void setOneClass(OneClass oneBean, SameType sameType) {
        this.oneClass = oneBean;
    }

    @ConditionalOnBean
    @Bean
    public NotInClass withConditionOnBean() {
        return new NotInClass();
    }
}
