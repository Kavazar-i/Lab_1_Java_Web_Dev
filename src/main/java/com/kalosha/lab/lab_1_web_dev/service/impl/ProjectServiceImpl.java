package com.kalosha.lab.lab_1_web_dev.service.impl;

import com.kalosha.lab.lab_1_web_dev.dao.ProjectDao;
import com.kalosha.lab.lab_1_web_dev.dao.impl.ProjectDaoImpl;
import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exception.DaoException;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;
import com.kalosha.lab.lab_1_web_dev.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private static ProjectServiceImpl instance = new ProjectServiceImpl();

    private ProjectDao projectDao = new ProjectDaoImpl();

    public ProjectServiceImpl() {
    }

    public static ProjectServiceImpl getInstance() {
        return instance;
    }

    public Project addProject(Project project) throws ServiceException {
        try {
            return projectDao.save(project);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add project", e);
        }
    }

    public Project getProjectById(int id) throws ServiceException {
        try {
            return projectDao.getProjectById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get project by id", e);
        }
    }

    public List<Project> getProjectsByUserId(int userId) throws ServiceException {
        try {
            return projectDao.getProjectsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get projects by user id", e);
        }
    }

    public void updateProject(Project project) throws ServiceException {
        try {
            projectDao.update(project);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update project", e);
        }
    }

    public void deleteProject(Project project) throws ServiceException {
        try {
            projectDao.delete(project);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete project", e);
        }
    }

    public void updatePhotoFilename(int projectId, String photoFilename) throws ServiceException {
        try {
            projectDao.updatePhotoFilename(projectId, photoFilename);
        } catch (Exception e) {
            throw new ServiceException("Failed to update photo filename", e);
        }
    }
}
