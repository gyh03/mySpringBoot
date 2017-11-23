package com.gyh.aspect;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyh.utils.JacksonUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


@Component
@Aspect
public class LogAspect {
	private static Logger log = Logger.getLogger(LogAspect.class);
	//配置切入点,拦截使用@OpeLogInfo注解的，记录日志
	@Pointcut("@annotation(com.gyh.aspect.OpeLogInfo))")
	public void aspect(){
	}

	/*
	 * 配置后置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@AfterReturning(pointcut = "aspect()",returning = "result")
	public void afterReturning(JoinPoint joinPoint,Object result) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//获取用户请求方法的参数
		StringBuffer params = new StringBuffer();
		Object[] args = joinPoint.getArgs();
		if (args!=  null && args.length > 0) {
			for ( int i = 0; i < args.length; i++) {
				if(joinPoint.getArgs()[i] instanceof MultipartFile){
					continue;
				}
				if(joinPoint.getArgs()[i] instanceof HttpServletResponse){
					continue;
				}
				if(joinPoint.getArgs()[i] instanceof HttpServletRequest){
					continue;
				}
				try {
					params.append(JacksonUtils.toJson(joinPoint.getArgs()[i])).append(";");
				} catch (Exception e) {
					continue;
				}
			}
		}
		try {
			//*========数据库日志=========*//
			Map<String,Object> annotation = getMethodDescription(joinPoint);
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("loginIp", getIpAddress(request));
			param.put("loginBrowser", request.getHeader("User-Agent"));
			if(annotation.get("node") !=null){
				param.put("node", annotation.get("node"));//操作
			}
			param.put("note", String.format("入参：【%s】，结果：【%s】",params.toString(), JacksonUtils.toJson(result)));//结果
			//记录到数据库中
//			System.out.println(JacksonUtils.toJson(param));
			log.error(JacksonUtils.toJson(param));
		}  catch (Exception e) {
			//记录本地异常日志
			e.printStackTrace();
			log.error("Aop记录日志报错:"+ e.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public  static Map<String,Object> getMethodDescription(JoinPoint joinPoint)  throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		//拦截的实体类
		Object target = joinPoint.getTarget();
		//拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		//拦截的方法参数类型
		Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
		Method method = target.getClass().getMethod(methodName, parameterTypes);
		OpeLogInfo ope = method.getAnnotation(OpeLogInfo.class);
		map.put("node", ope.node());
		return map;
	}
	/**
	 * 获取对象的真实IP地址
	 *
	 */
	public  String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_REAL_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
