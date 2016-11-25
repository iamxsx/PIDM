package com.oneclouder.pidm.index.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by clouder on 16-10-29.
 */
public class CacheFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Date date = new Date();
        //Last-Modified:页面的最后生成时间
        response.setDateHeader("Last-Modified",date.getTime());
        //Expires:过时期限值
        response.setDateHeader("Expires",date.getTime()+20000);
        //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
        response.setHeader("Cache-Control", "public");
        //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存java.util.Date date = new java.util.Date();
        response.setHeader("Pragma", "Pragma");
        //Last-Modified:页面的最后生成时间
        response.setDateHeader("Last-Modified",date.getTime());
        //Expires:过时期限值
        response.setDateHeader("Expires",date.getTime()+20000);
        //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
        response.setHeader("Cache-Control", "public");
        //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存
        response.setHeader("Pragma", "Pragma");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
