package com.gyh.base.exception;

import com.gyh.base.GyhResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

/**
 * @author gyh
 * 统一异常处理
 */
@ControllerAdvice
public class BaseCotrollerException {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @Value("#{prop['maxUploadSize']}")
    private Long maxUploadSize;*/

    /**
     * 拦截Controller中抛出的异常，并返回错误信息
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object customException(HttpServletRequest request, Exception ex) {
        GyhResult result = null;
        if (ex instanceof NoPrintException) {
            result = new GyhResult(false, ((NoPrintException) ex).getCode(), ex.getMessage(), null);
            return result;
        }
        logger.error("controller has an ex:", ex);
        if (ex instanceof GyhException) {
            result = new GyhResult(false, ((GyhException) ex).getCode(), ex.getMessage(), null);
            return result;
        }
        result = new GyhResult(false, GyhException.SERVER_INTERNAL, ex.getMessage(), null);
        return result;
    }

    @ExceptionHandler(BindException.class)
    public Object customException(){
        return "redirect:https://www.baidu.com/";
    }
}
