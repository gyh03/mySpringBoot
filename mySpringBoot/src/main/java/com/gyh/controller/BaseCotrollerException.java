package com.gyh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gyh
 * 统一异常处理
 */
@ControllerAdvice
public class BaseCotrollerException {

   /* @Value("#{prop['maxUploadSize']}")
    private Long maxUploadSize;*/
    /**
     * 拦截Controller中抛出的异常，并返回错误信息
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object CustomExceptionMes(HttpServletRequest request, Exception ex){
        System.out.println("......>");
        Map<String,Object> res =new HashMap<>();
        res.put("isSuccess",false);
        res.put("msg",ex.getMessage());
        return res;
    }

}
