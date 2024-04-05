package com.kalosha.lab.lab_1_web_dev.exeption;

public class CommandExeption extends Exception {
    public CommandExeption() {
    }

    public CommandExeption(String message) {
        super(message);
    }

    public CommandExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExeption(Throwable cause) {
        super(cause);
    }
}
