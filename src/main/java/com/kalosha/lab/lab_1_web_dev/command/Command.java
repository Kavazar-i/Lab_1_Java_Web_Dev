package com.kalosha.lab.lab_1_web_dev.command;

import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@FunctionalInterface
public interface Command  {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException, ServiceException;
}
