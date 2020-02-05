package com.gyh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * start the Spring
 *
 * @author gyh
 * @SpringBootApplication 启动springboot
 * @ServletComponentScan Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
 * filter > interceptor > aop
 *
 * //@ComponentScan("com.gyh.*")
 * //@MapperScan("com.gyh.*.mapper")
 *
 * 使用@MapperScan扫描包后，会自动将包内所有接口作为mapper，不必再为每个Mapper接口使用@Mapper；
 * 若不使用@MapperScan扫描包，MybatisAutoConfiguration.java自动配置类，会查找使用所有使用@Mapper注解的接口，作为mapper。
 */
@SpringBootApplication
@MapperScan("com.gyh.*.mapper")
public class GyhApp {
    public static void main(String[] args) {
        SpringApplication.run(GyhApp.class, args);
        int a=0;
        System.out.println("【【【【【【 start success ...】】】】】】");
    }
}
