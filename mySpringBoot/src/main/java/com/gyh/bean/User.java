package com.gyh.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
