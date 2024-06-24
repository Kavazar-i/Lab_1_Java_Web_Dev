package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;
import com.kalosha.lab.lab_1_web_dev.service.ProjectService;
import com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ProfileCommand implements Command {
    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = (int) request.getSession().getAttribute("userId");
        List<Project> projects = null;
        try {
            projects = projectService.getProjectsByUserId(userId);
        } catch (ServiceException e) {
            throw new CommandException("Failed to get projects by user id", e);
        }
        request.setAttribute("projects", projects);
        return new Router("profile.jsp", Router.Type.FORWARD);
    }
}
