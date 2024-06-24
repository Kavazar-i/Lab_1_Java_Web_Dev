package com.kalosha.lab.lab_1_web_dev.service.impl;

import com.kalosha.lab.lab_1_web_dev.dao.UserDao;
import com.kalosha.lab.lab_1_web_dev.dao.impl.UserDaoImpl;
import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exception.DaoException;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;
import com.kalosha.lab.lab_1_web_dev.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserDao userDao = new UserDaoImpl();

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    public void registerUser(User user) throws ServiceException {
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
    }

    public User getUserById(int id) throws ServiceException {
        try {
            return userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user by id", e);
        }
    }

    public User getUserByUsername(String username) throws ServiceException {
        try {
            return userDao.getUserByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user by username", e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all users", e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update user", e);
        }
    }

    public void deleteUser(User user) throws ServiceException {
        try {
            userDao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
    }

    @Override
    public boolean authenticate(String username, String password) throws ServiceException {
        //TODO: Implement valodation of login and password + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
