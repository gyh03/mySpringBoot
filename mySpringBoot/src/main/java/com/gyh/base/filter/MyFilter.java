package com.gyh.base.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 使用注解标注过滤器
 *
 * @author gyh
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称, 可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @Order 多个过滤器时，配置@Order的值，设置filter的顺序，会按照order值的大小，从小到大的顺序来依次过滤
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
//@Order(Integer.MAX_VALUE)
public class MyFilter implements Filter {

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