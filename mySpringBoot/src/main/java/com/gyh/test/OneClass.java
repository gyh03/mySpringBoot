package com.gyh.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 一个class
 *
 * @author guoyanhong5
 * @date 2021-03-09 17:39
 */
@Component
public class OneClass {
    private SameType sameType;

    /**
     * Autowired 标注的方法的参数也会被自动依赖
     *
     * @param sameType
     */
    @Autowired
    public void injectOneBean(SameType sameType) {
        this.sameType = sameType;
    }
}
