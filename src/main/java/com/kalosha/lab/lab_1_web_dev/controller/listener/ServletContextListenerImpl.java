package com.kalosha.lab.lab_1_web_dev.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j;

@Log4j
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("----------> Servlet context initialized : " + sce.getServletContext().getServerInfo() + " <----------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("----------> Servlet context destroyed : " + sce.getServletContext().getContextPath() + " <----------");
    }
}
