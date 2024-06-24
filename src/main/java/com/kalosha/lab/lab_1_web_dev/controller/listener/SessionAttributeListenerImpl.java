package com.kalosha.lab.lab_1_web_dev.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import lombok.extern.log4j.Log4j;

@Log4j
@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        log.info("----------> Session attribute added : " + event.getSession().getAttribute("username") + " <----------");
        log.info("----------> Session attribute added : " + event.getSession().getAttribute("command") + " <----------");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        log.info("----------> Session attribute removed : " + event.getSession().getId());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        log.info("----------> Session attribute replaced : " + event.getSession().getAttribute("user_name") + " <----------");
        log.info("----------> Session attribute replaced : " + event.getSession().getAttribute("command") + " <----------");
    }
}
