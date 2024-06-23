package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoException;

public interface UserDao {
    User getUserById(int id) throws DaoException;

    User getUserByUsername(String username) throws DaoException;

    boolean authenticate(String username, String password) throws DaoException;
}