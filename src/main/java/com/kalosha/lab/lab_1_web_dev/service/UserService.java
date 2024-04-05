package com.kalosha.lab.lab_1_web_dev.service;

import com.kalosha.lab.lab_1_web_dev.exeption.ServiceExeption;

public interface UserService {
    boolean authenticate(String username, String password) throws ServiceExeption;
}
