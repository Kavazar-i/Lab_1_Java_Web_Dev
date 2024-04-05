package com.kalosha.lab.lab_1_web_dev.exeption;

public class DaoExeption extends Exception{
    public DaoExeption() {
    }

    public DaoExeption(String message) {
        super(message);
    }

    public DaoExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoExeption(Throwable cause) {
        super(cause);
    }
}
