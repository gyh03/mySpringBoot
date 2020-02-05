package com.gyh.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试动态修改日志级别
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String simple() {
        log.debug("这是一个debug日志...");
        log.info("这是一个info日志...");
        log.error("这是一个error日志...");
        return "test";
    }
}