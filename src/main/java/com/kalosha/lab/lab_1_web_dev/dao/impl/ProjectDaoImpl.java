package com.kalosha.lab.lab_1_web_dev.dao.impl;

import com.kalosha.lab.lab_1_web_dev.dao.BaseDao;
import com.kalosha.lab.lab_1_web_dev.dao.ProjectDao;
import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exception.DaoException;
import com.kalosha.lab.lab_1_web_dev.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDao<Project> implements ProjectDao {
    private static final String FIND_ALL_PROJECTS_QUERY = "SELECT * FROM projects";
    private static final String ADD_PROJECT_QUERY = "INSERT INTO projects (user_id, title, description, photo_filename, project_url) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_PROJECT_BY_ID_QUERY = "SELECT * FROM projects WHERE id = ?";
    private static final String FIND_PROJECT_BY_TITLE_AND_DESCRIPTION_QUERY = "SELECT * FROM projects WHERE title = ? AND description = ?";
    private static final String FIND_PROJECTS_BY_USER_ID_QUERY = "SELECT * FROM projects WHERE user_id = ?";
    private static final String UPDATE_PROJECT_QUERY = "UPDATE projects SET title = ?, description = ?, photo_filename = ?, project_url = ? WHERE id = ?";
    private static final String DELETE_PROJECT_QUERY = "DELETE FROM projects WHERE id = ?";
    private static final String UPDATE_PHOTO_FILENAME = "UPDATE projects SET photo_filename = ? WHERE id = ?";

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
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        }
        return projects;
    }

    @Override
    public Project save(Project project) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(ADD_PROJECT_QUERY)) {
            stmt.setInt(1, project.getUserId());
            stmt.setString(2, project.getTitle());
            stmt.setString(3, project.getDescription());
            stmt.setString(4, project.getPhotoFilename());
            stmt.setString(5, project.getProjectUrl());
            stmt.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error while adding project", e);
        }
        return getProjectByTitleAndDescription(project.getTitle(), project.getDescription());
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
            ConnectionPool.getInstance().releaseConnection(connection);
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
            ConnectionPool.getInstance().releaseConnection(connection);
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
            stmt.setString(3, project.getPhotoFilename());
            stmt.setString(4, project.getProjectUrl());
            stmt.setInt(5, project.getId());
            stmt.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
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
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error while deleting project", e);
        }
    }

    private Project fillEmptyProjectWithData(ResultSet rs) throws DaoException {
        Project project = new Project();

        try {
            project.setId(rs.getInt("id"));
            project.setUserId(rs.getInt("user_id"));
            project.setTitle(rs.getString("title"));
            project.setDescription(rs.getString("description"));
            project.setPhotoFilename(rs.getString("photo_filename"));
            project.setProjectUrl(rs.getString("project_url"));
            return project;
        } catch (SQLException e) {
            throw new DaoException("Error while filling project with data", e);
        }
    }

    @Override
    public void updatePhotoFilename(int projectId, String photoFilename) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PHOTO_FILENAME)) {
            statement.setString(1, photoFilename);
            statement.setInt(2, projectId);
            statement.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error while updating photo filename", e);
        }
    }

    private Project getProjectByTitleAndDescription(String title, String description) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(FIND_PROJECT_BY_TITLE_AND_DESCRIPTION_QUERY)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return fillEmptyProjectWithData(rs);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error while getting project by title and description", e);
        }
        return null;
    }
}
