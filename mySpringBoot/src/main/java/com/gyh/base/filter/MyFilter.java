package com.gyh.base.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 *
 * @author guoyanhong
 * @date 2018/9/20 15:46
 * <p>
 * springboot 使用 FilterRegistrationBean 来注册filter
 * 使用@WebFilter注解，在spring启动时，会默认为其创建一个 FilterRegistrationBean 并将filter set 进去，
 * 此时通过@Order将不能改变filter的顺序，以为所有filter加载的时候，自定义的Filter 会按照其 FilterRegistrationBean 的order来排序，而默认创建的 FilterRegistrationBean 的order 都是 Integer的最大值
 * 想要修改filter的顺序，必须自定义FilterRegistrationBean 并设置Order，详情参考 MyFilterRegistration.java
 * <p>
 * //@WebFilter 将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称, 可选
 * //属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * //@WebFilter(filterName = "myFilter", urlPatterns = "/*")
 * //@Order(1) 此处使用@Order将无效
 */
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("create MyFilter ...");
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("filter pre doFilter ... 此处可以做 过滤敏感词、设置字符编码、url级别权限访问等控制");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
        System.out.println("filter init");
    }

}