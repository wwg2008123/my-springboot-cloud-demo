package com.wwg.servicedemo01.common.filter;

import javax.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("filter1==>befor doFilter:" + format.format(new Date()));
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter1==>after doFilter:" + format.format(new Date()));

    }

    @Override
    public void destroy() {

    }
}
