package com.kalosha.lab.lab_1_web_dev.command;

import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command  {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
