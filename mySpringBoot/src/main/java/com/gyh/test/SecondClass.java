package com.gyh.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 第二个class
 *
 * @author guoyanhong5
 * @date 2021-03-09 17:39
 */
@Component
public class SecondClass {
    private SameType sameType;
    private List<SameType> sameTypes;
    private OneClass oneClass;
    /**
      当构造函数使用了 Autowired 注解且 required = true(该构造函数默认被容器调用)，那么其他构造函数将不能是用Autowired(即使required = false 也不行)；
      若多个构造函数使用了 Autowired 注解且 required 都是 false，容器将选择一个最优的构造函数，选择规则是：public的，参数个数多的，依赖的参数对象存在的。
     */


    /**
     * 构造函数1
     *
     * @param sameType
     */
    @Autowired(required = false)
    private SecondClass(SameType sameType, OneClass oneClass) {
        this.sameType = sameType;
    }

    /**
     * 构造函数2
     *
     * @param sameTypes
     */
    @Autowired(required = false)
    public SecondClass(List<SameType> sameTypes) {
        this.sameTypes = sameTypes;
    }

    /**
     * 构造函数3
     *
     * @param sameTypes
     * @param oneClass
     */
    @Autowired(required = false)
    public SecondClass(List<SameType> sameTypes, OneClass oneClass) {
        this.sameTypes = sameTypes;
        this.oneClass = oneClass;
    }
}
