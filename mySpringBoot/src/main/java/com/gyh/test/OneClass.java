package com.gyh.test;

import com.gyh.user.mapper.UserMapper;
import com.gyh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 一个class
 *
 * @author guoyanhong5
 * @date 2021-03-09 17:39
 */
@Component
public class OneClass {
    private SameType sameType;
    private List<SameType> sameTypes;
    private UserService userService;

    /**
     * 如果只有一个构造函数时，即使不使用Autowired 注解，参数会默认自动依赖
     *
     * @param sameTypes
     */
    public OneClass(List<SameType> sameTypes,UserService userService) {
        this.sameTypes = sameTypes;
    }


    /**
     * Autowired 标注的方法的参数也会被自动依赖
     *
     * @param sameType
     */
    @Autowired
    private void injectOneBean(SameType sameType) {
        this.sameType = sameType;
    }
}
