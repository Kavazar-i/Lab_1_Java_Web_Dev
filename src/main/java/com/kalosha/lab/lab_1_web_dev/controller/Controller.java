package com.kalosha.lab.lab_1_web_dev.controller;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.CommandType;
import com.kalosha.lab.lab_1_web_dev.exeption.CommandExeption;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controller", value = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private String message;

    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
//            request.getRequestDispatcher(page).forward(request, response);//TODO: F5 sec
            response.sendRedirect(request.getContextPath() + page);//TODO: F5 sec
        } catch (CommandExeption e) {
            response.sendError(500, e.getMessage()); //TODO: use as error handler
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}