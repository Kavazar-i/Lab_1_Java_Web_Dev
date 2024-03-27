package com.kalosha.lab.lab_1_web_dev.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command  {
    String execute(HttpServletRequest request);
    default void refresh(){}
}
