package com.gyh.base.aop;

import com.gyh.base.GyhConstant;
import com.gyh.base.spring.db.DataSourceHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guoyanhong
 * @date 2018/9/18 14:52
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    @Pointcut("execution(*  com.gyh..service.*.*(..))")
    public void pointcut() {
    }

    /**
     * 切库
     */
    @Before("pointcut()")
    public void changeDb() {
        // TODO 获取当前登录用户信息，路由得知dbId，此处从request中获取来模拟
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String dbId = request.getParameter("dbId");
        if(StringUtils.isBlank(dbId)){
            dbId = GyhConstant.MAIN_DB_ID;
        }
        DataSourceHolder.setDbId(dbId);
    }
}
