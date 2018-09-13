package com.gyh.user.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GyhUser {
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public GyhUser(String name) {
        this.name = name;
    }

    public GyhUser() {
    }
}
