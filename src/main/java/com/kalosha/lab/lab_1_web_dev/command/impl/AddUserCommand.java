package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router("", Router.Type.FORWARD);
    }
}
