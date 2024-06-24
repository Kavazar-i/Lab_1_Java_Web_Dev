package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;
import com.kalosha.lab.lab_1_web_dev.service.UserService;
import com.kalosha.lab.lab_1_web_dev.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpdateProfileCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException, ServiceException {
        String userId = request.getParameter("user_id");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String skills = request.getParameter("skills");


        User user = userService.getUserById(Integer.parseInt(userId));

        user.setFullname(fullname);
        user.setEmail(email);
        user.setBio(bio);
        user.setSkills(skills);

        try {
            userService.updateUser(user);
            request.getSession().setAttribute("user", user);
            return new Router("profile.jsp", Router.Type.REDIRECT);
        } catch (Exception e) {
            return new Router("pages/error/registrationError.jsp", Router.Type.FORWARD);
        }    }
}
