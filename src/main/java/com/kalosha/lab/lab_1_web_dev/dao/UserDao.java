package com.kalosha.lab.lab_1_web_dev.dao;

public interface UserDao {
    boolean authenticate(String username, String password);
}
