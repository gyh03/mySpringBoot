package com.gyh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * start the Spring
 * @author gyh
 */
@SpringBootApplication
//@ComponentScan("com.gyh.*")
//@MapperScan("com.gyh.*")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
