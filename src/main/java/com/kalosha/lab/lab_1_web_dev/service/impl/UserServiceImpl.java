package com.kalosha.lab.lab_1_web_dev.service.impl;

import com.kalosha.lab.lab_1_web_dev.dao.impl.UserDaoImpl;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoExeption;
import com.kalosha.lab.lab_1_web_dev.exeption.ServiceExeption;
import com.kalosha.lab.lab_1_web_dev.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String username, String password) throws ServiceExeption {
        //TODO: Implement valodation of login and password + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(username, password);
        } catch (DaoExeption e) {
            throw new ServiceExeption(e);
        }
        return match;
    }
}
