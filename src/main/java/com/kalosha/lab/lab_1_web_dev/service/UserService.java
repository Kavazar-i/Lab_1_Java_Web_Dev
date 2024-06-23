package com.kalosha.lab.lab_1_web_dev.service;

import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exeption.ServiceException;

import java.util.List;

public interface UserService {
    public void registerUser(User user) throws ServiceException;

    public User getUserById(int id) throws ServiceException;

    public User getUserByUsername(String username) throws ServiceException;

    public List<User> getAllUsers() throws ServiceException;

    public void updateUser(User user) throws ServiceException;

    public void deleteUser(User user) throws ServiceException;

    boolean authenticate(String username, String password) throws ServiceException;
}
