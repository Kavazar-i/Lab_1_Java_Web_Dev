package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.exeption.CommandExeption;
import com.kalosha.lab.lab_1_web_dev.exeption.ServiceExeption;
import com.kalosha.lab.lab_1_web_dev.service.UserService;
import com.kalosha.lab.lab_1_web_dev.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandExeption {
        String username = request.getParameter("username"); //TODO: To constants
        String password = request.getParameter("pass"); //TODO: To constants
        UserService userService = UserServiceImpl.getInstance();
        String page;

        try {
            if(userService.authenticate(username, password)) {
                request.setAttribute("user", username);
                page = "pages/main.jsp"; //TODO: To constants (PagePasses class?)
            } else {
                request.setAttribute("login_error_msg", "Invalid username or password"); //TODO: To constants (PagePasses class?)
                page = "index.jsp"; //TODO: To constants (PagePasses class?)
            }
        } catch (ServiceExeption e) {
            throw new CommandExeption(e);
        }
        return page;
    }
}
