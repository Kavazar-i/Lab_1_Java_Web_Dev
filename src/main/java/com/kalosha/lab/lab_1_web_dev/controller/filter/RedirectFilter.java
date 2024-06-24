//package com.kalosha.lab.lab_1_web_dev.controller.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.annotation.WebInitParam;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.log4j.Log4j;
//
//import java.io.IOException;
//
//@Log4j
//@WebFilter(urlPatterns = {"/pages/*"}, initParams = {
//        @WebInitParam(name = "INDEX_PATH", value = "/index.jsp")
//})
//public class RedirectFilter implements Filter {
//    private String indexPath;
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//        indexPath = filterConfig.getInitParameter("INDEX_PATH");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//
//        boolean loggedIn = httpRequest.getSession().getAttribute("user") != null;
//
//        if (!loggedIn) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
//            filterChain.doFilter(httpRequest, httpResponse);
//        }
//    }
//
//    public void destroy() {
//    }
//}
