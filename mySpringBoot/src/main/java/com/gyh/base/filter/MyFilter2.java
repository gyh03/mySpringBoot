package com.gyh.base.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 使用注解标注过滤器
 * 测试filter 加载顺序
 *
 * @author guoyanhong
 * @date 2018/9/20 13:59
 */
public class MyFilter2 implements Filter {

    @Override
    public void destroy() {
        System.out.println("filter2 destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("filter pre doFilter2 ...");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
        System.out.println("filter2 init");
    }

}