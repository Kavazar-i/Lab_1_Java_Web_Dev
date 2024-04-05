package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.exeption.DaoExeption;

public interface UserDao {
    boolean authenticate(String username, String password) throws DaoExeption;
}
