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

public class LoginCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.getUserByUsername(username);
            if (user != null && user.getPassword().equals(HashUtil.hashPassword(password))) {
                request.getSession().setAttribute("user", user);
                return new Router("pages/profile.jsp", Router.Type.REDIRECT);
            } else {
                return new Router("pages/error/loginError.jsp", Router.Type.FORWARD);
            }
        } catch (Exception e) {
            return new Router("pages/error/loginError.jsp", Router.Type.FORWARD);
        }


//        String page;
//        try {
//            if(UserServiceImpl.getInstance().authenticate(username, password)) {
//                request.setAttribute("user", username);
//                request.getSession().setAttribute("user_name", username);
//                page = "/pages/main.jsp"; //TODO: To constants (PagePasses class?)
//            } else {
//                request.setAttribute("login_error_msg", "Invalid username or password"); //TODO: To constants (PagePasses class?)
//                page = "/index.jsp"; //TODO: To constants (PagePasses class?)
//            }
//            request.getSession().setAttribute("current_page", page);
//        } catch (ServiceException e) {
//            throw new CommandException(e);
//        }
//        return page;
    }
}
