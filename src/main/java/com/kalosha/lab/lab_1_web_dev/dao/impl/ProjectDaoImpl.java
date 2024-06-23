package com.kalosha.lab.lab_1_web_dev.dao.impl;

import com.kalosha.lab.lab_1_web_dev.dao.BaseDao;
import com.kalosha.lab.lab_1_web_dev.dao.ProjectDao;
import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoException;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDao<Project> implements ProjectDao {
    private static final String FIND_ALL_PROJECTS_QUERY = "SELECT * FROM projects";
    private static final String ADD_PROJECT_QUERY = "INSERT INTO projects (user_id, title, description, image_url, project_url) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_PROJECT_BY_ID_QUERY = "SELECT * FROM projects WHERE id = ?";
    private static final String FIND_PROJECTS_BY_USER_ID_QUERY = "SELECT * FROM projects WHERE user_id = ?";
    private static final String UPDATE_PROJECT_QUERY = "UPDATE projects SET title = ?, description = ?, image_url = ?, project_url = ? WHERE id = ?";
    private static final String DELETE_PROJECT_QUERY = "DELETE FROM projects WHERE id = ?";
    private static final ProjectDaoImpl instance = new ProjectDaoImpl();

    public static ProjectDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Project> findAll() throws DaoException {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_ALL_PROJECTS_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(fillEmptyProjectWithData(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        }
        return projects;
    }

    @Override
    public Project create(Project project) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(ADD_PROJECT_QUERY)) {
            stmt.setInt(1, project.getUserId());
            stmt.setString(2, project.getTitle());
            stmt.setString(3, project.getDescription());
            stmt.setString(4, project.getImageUrl());
            stmt.setString(5, project.getProjectUrl());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while adding project", e);
        }
        return project;
    }

    @Override
    public Project getProjectById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_PROJECT_BY_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return fillEmptyProjectWithData(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting project by id", e);
        }
        return null;
    }

    @Override
    public List<Project> getProjectsByUserId(int userId) throws DaoException {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_PROJECTS_BY_USER_ID_QUERY)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projects.add(fillEmptyProjectWithData(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting projects by user id", e);
        }
        return projects;
    }

    @Override
    public Project update(Project project) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_PROJECT_QUERY)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getImageUrl());
            stmt.setString(4, project.getProjectUrl());
            stmt.setInt(5, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while updating project", e);
        }
        return project;
    }

    @Override
    public void delete(Project project) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_PROJECT_QUERY)) {
            stmt.setInt(1, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while deleting project", e);
        }
    }

    private Project fillEmptyProjectWithData(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setUserId(rs.getInt("user_id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("image_url"));
        project.setProjectUrl(rs.getString("project_url"));
        return project;
    }
}
