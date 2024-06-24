package com.kalosha.lab.lab_1_web_dev.command;

import com.kalosha.lab.lab_1_web_dev.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    ADD_PROJECT(new AddProjectCommand()),
    UPDATE_PROJECT(new UpdateProjectCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
