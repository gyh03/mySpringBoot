package com.gyh.base.aop;

import com.gyh.utils.IpUtils;
import com.gyh.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


@Component
@Aspect
@Slf4j
public class OpeLogAspect {

    /**
     * 配置切入点,拦截使用@OpeLogInfo注解的，记录日志
     */
    @Pointcut("@annotation(com.gyh.base.aop.OpeLogInfo))")
    public void aspect() {
    }

    /**
     * 配置后置通知,使用在方法aspect()上注册的切入点,同时接受JoinPoint切入点对象,可以没有该参数
     *
     * @param joinPoint
     * @param result
     * @throws Throwable
     */
    @AfterReturning(pointcut = "aspect()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户请求方法的参数
        StringBuffer params = new StringBuffer();
        Object[] args = joinPoint.getArgs();
        Stream.of(args).filter(arg -> !(arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse)).forEach(arg -> {
            params.append(JacksonUtils.toJson(arg)).append(";");
        });
        try {
            //*========数据库日志=========*//
            Map<String, Object> annotation = getMethodDescription(joinPoint);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("loginIp", IpUtils.getUserIpAddress(request));
            param.put("loginBrowser", request.getHeader("User-Agent"));
            if (annotation.get("node") != null) {
                //操作
                param.put("node", annotation.get("node"));
            }
            //结果
            param.put("note", String.format("入参：【%s】，结果：【%s】", params.toString(), JacksonUtils.toJson(result)));
            //记录到数据库中
            System.out.println(JacksonUtils.toJson(param));
        } catch (Exception e) {
            //记录本地异常日志
            e.printStackTrace();
            log.error("Aop记录日志报错:" + e.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getMethodDescription(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //拦截的实体类
        Object target = joinPoint.getTarget();
        //拦截的方法名称
        String methodName = joinPoint.getSignature().getName();
        //拦截的方法参数类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        OpeLogInfo ope = method.getAnnotation(OpeLogInfo.class);
        map.put("node", ope.node());
        return map;
    }

}
