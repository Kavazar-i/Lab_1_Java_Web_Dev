package com.kalosha.lab.lab_1_web_dev.dao.impl;

import com.kalosha.lab.lab_1_web_dev.dao.BaseDao;
import com.kalosha.lab.lab_1_web_dev.dao.UserDao;
import com.kalosha.lab.lab_1_web_dev.entity.User;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoException;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String QUERY = "SELECT password FROM users WHERE username = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO users (username, password, fullname, email, , skills) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_USER_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, password = ?, fullname = ?, email = ?, bio = ?, skills = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    private static UserDaoImpl instance = new UserDaoImpl();

    public UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public User create(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(ADD_USER_QUERY)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getBio());
            stmt.setString(6, user.getSkills());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while creating user", e);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(FIND_USER_BY_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return fillEmptyUserWithData(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by id", e);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(FIND_USER_BY_USERNAME_QUERY)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return fillEmptyUserWithData(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by username", e);
        }
        return null;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(FIND_ALL_USERS_QUERY); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(fillEmptyUserWithData(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        }
        return users;
    }

    @Override
    public User update(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE_USER_QUERY)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getBio());
            stmt.setString(6, user.getSkills());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while updating user", e);
        }
        return user;
    }

    @Override
    public void delete(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE_USER_QUERY)) {
            stmt.setInt(1, user.getId());
        } catch (SQLException e) {
            throw new DaoException("Error while deleting user", e);
        }
    }

    @Override
    public boolean authenticate(String username, String password) throws DaoException {

        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            String passwordFromDB = "";

            if (resultSet.next()) {
                passwordFromDB = resultSet.getString(1);
                match = password.equals(passwordFromDB);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL authenticate failed: ", e);
        }

        return match;
    }

    private User fillEmptyUserWithData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullname(rs.getString("fullname"));
        user.setEmail(rs.getString("email"));
        user.setBio(rs.getString("bio"));
        user.setSkills(rs.getString("skills"));
        return user;
    }
}
