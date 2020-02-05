package com.gyh.user.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "person")
//@PropertySource(value = {"classpath:application.properties"})
@Data
public class TempPerson {
    private String name;
    private String fullName;
}
