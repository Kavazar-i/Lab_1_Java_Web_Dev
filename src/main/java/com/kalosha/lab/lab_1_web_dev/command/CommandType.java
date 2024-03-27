package com.kalosha.lab.lab_1_web_dev.command;

import com.kalosha.lab.lab_1_web_dev.command.impl.AddUserCommand;
import com.kalosha.lab.lab_1_web_dev.command.impl.DefaultCommand;
import com.kalosha.lab.lab_1_web_dev.command.impl.LoginCommand;
import com.kalosha.lab.lab_1_web_dev.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
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
