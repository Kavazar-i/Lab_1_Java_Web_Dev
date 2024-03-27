package com.kalosha.lab.lab_1_web_dev.dao.impl;

import com.kalosha.lab.lab_1_web_dev.dao.BaseDao;
import com.kalosha.lab.lab_1_web_dev.dao.UserDao;
import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    //    private static final Logger logger = Logger.getLogger(MultiplyServlet.class); //TODO: Discuss

    private static final String QUERY = "SELECT password FROM users WHERE username = ?";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) {

        boolean match = false;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY)
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            String passwordFromDB = "";

            if (resultSet.next()) {
                passwordFromDB = resultSet.getString(1);
                match = password.equals(passwordFromDB);
            }
        } catch (SQLException e) {
            log.error("Connection failed:", e);
        }

        return match;
    }
}
