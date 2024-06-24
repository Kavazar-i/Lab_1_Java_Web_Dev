package com.kalosha.lab.lab_1_web_dev.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.log4j.Log4j;

@Log4j
@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("----------> Session created : " + se.getSession().getId() + " <----------");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("----------> Session destroyed : " + se.getSession().getId() + " <----------");
    }
}
