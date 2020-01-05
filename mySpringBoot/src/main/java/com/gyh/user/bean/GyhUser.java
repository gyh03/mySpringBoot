package com.gyh.user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
public class GyhUser {
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthday;

    @NumberFormat(pattern = "###,###.##")
    private Number money;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public GyhUser(String name) {
        this.name = name;
    }

    public GyhUser() {
    }
}
