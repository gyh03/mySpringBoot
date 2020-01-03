package com.gyh.base.spring.config;

import com.gyh.user.bean.GyhUser;
import com.gyh.user.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring 配置类
 *
 * @author guoyanhong
 * @date 2019/12/30 20:20
 */
@Configuration
public class SpringConfigurationExt {
    /**
     * 测试 @Bean 入参实例化
     * 测试 @ConditionOnBean 的注入顺序
     *
     * @param userService
     * @return
     */
//    @Bean
//    @ConditionalOnBean(xxx.class)
    public GyhUser testUser(UserService userService) {
        GyhUser tesstUser = userService.getUsersByName("gyh").stream().findFirst().orElse(new GyhUser("testUser"));
        return tesstUser;
    }
}
