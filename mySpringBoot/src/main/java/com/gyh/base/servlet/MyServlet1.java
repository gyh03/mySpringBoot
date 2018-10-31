package com.gyh.base.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author guoyanhong
 * @date 2018/10/31 17:57
 */
public class MyServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------doPost1----------------");
        resp.getWriter().print("<h1>Hello MyServlet1 Response return you this</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("-----------servlet 1 destory----------------");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("-----------servlet 1 init----------------");
        super.init();
    }
}
