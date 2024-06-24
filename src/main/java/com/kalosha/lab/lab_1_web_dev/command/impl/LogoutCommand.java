package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        try {
            return new Router("login.jsp", Router.Type.REDIRECT);
        } catch (Exception e) {
            return new Router("pageNotFound.jsp", Router.Type.FORWARD);
        }
    }
}
