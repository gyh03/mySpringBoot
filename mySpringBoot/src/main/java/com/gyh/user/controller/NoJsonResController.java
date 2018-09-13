package com.gyh.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guoyanhong
 * @date 2018/9/12 14:31
 */
@Controller
public class NoJsonResController {

    /**
     * 重定向
     *
     * @return
     */
    @RequestMapping("goOtherPage")
    public String goOtherPage() {
        return "redirect:https://baidu.com";
    }
}
