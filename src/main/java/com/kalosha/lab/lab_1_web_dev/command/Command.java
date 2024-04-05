package com.kalosha.lab.lab_1_web_dev.command;

import com.kalosha.lab.lab_1_web_dev.exeption.CommandExeption;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command  {
    String execute(HttpServletRequest request) throws CommandExeption;
    default void refresh(){}
}
