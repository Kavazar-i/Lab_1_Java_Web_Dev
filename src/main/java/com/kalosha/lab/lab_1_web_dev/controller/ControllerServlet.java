package com.kalosha.lab.lab_1_web_dev.controller;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.CommandType;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
@WebServlet(name = "controller", value = {"/controller", "*.do"})
public class ControllerServlet extends HttpServlet {

    public void init() {
        ConnectionPool.getInstance();
        log.info("Servlet " + this.getServletName() + " has started");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandStr = request.getAttribute("command").toString();
        Command command = CommandType.define(commandStr);

        try {
            Router router = command.execute(request, response);
            if (router.getType() == Router.Type.REDIRECT) {
                response.sendRedirect(router.getPage());
            } else {
                request.getRequestDispatcher(router.getPage()).forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Command execution failed", e);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        log.info("Servlet " + this.getServletName() + " has stopped");
    }
}

