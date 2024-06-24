package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exception.DaoException;

import java.util.List;

public interface UserDao {
    public abstract List<User> findAll() throws DaoException;

    public abstract User save(User user) throws DaoException;

    public abstract User update(User user) throws DaoException;

    public abstract void delete(User user) throws DaoException;

    User getUserById(int id) throws DaoException;

    User getUserByUsername(String username) throws DaoException;

    boolean authenticate(String username, String password) throws DaoException;
}