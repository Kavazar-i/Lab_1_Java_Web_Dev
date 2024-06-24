package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import com.kalosha.lab.lab_1_web_dev.service.UserService;
import com.kalosha.lab.lab_1_web_dev.service.impl.UserServiceImpl;
import com.kalosha.lab.lab_1_web_dev.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String skills = request.getParameter("skills");

        String hashedPassword = HashUtil.hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setBio(bio);
        user.setSkills(skills);

        try {
            userService.registerUser(user);
            return new Router("login.jsp", Router.Type.REDIRECT);
        } catch (Exception e) {
            return new Router("pages/error/registrationError.jsp", Router.Type.FORWARD);
        }
    }
}
