package com.gyh.user.controller;

import com.gyh.base.exception.GyhException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author guoyanhong
 * @date 2018/9/12 14:31
 */
@Controller
public class NoJsonResController {

    private String redirctUrl = "https://baidu.com";

    /**
     * 重定向
     *
     * @return
     */
    @RequestMapping("goOtherPage")
    public String goOtherPage() {
        return "redirect:" + redirctUrl;
    }

    /**
     * 重定向
     *
     * @return
     */
    @RequestMapping("goOtherPage2")
    public void goOtherPage2(HttpServletResponse response) throws IOException {
        response.sendRedirect(redirctUrl);
    }
    @RequestMapping("throwAnException")
    public void throwAnException(){
        throw new GyhException("this is an exception");
    }
}
