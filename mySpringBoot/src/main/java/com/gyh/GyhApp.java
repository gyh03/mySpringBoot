package com.gyh;


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
 * //@MapperScan("com.gyh.*")
 */
@SpringBootApplication
@ServletComponentScan
public class GyhApp {
    public static void main(String[] args) {
        SpringApplication.run(GyhApp.class, args);
        int a=0;
        System.out.println("【【【【【【 start success ...】】】】】】");
    }
}
