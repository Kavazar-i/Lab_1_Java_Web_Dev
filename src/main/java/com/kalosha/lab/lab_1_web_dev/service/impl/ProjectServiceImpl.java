package com.kalosha.lab.lab_1_web_dev.service.impl;

import com.kalosha.lab.lab_1_web_dev.dao.ProjectDao;
import com.kalosha.lab.lab_1_web_dev.dao.impl.ProjectDaoImpl;
import com.kalosha.lab.lab_1_web_dev.entity.Project;

import java.util.List;

public class ProjectServiceImpl {
    private static ProjectServiceImpl instance = new ProjectServiceImpl();

    private ProjectDao projectDao = new ProjectDaoImpl();

    private ProjectServiceImpl() {
    }

    public static ProjectServiceImpl getInstance() {
        return instance;
    }

    public void addProject(Project project) throws Exception {
        projectDao.create(project);
    }

    public Project getProjectById(int id) throws Exception {
        return projectDao.getProjectById(id);
    }

    public List<Project> getProjectsByUserId(int userId) throws Exception {
        return projectDao.getProjectsByUserId(userId);
    }

    public void updateProject(Project project) throws Exception {
        projectDao.update(project);
    }

    public void deleteProject(Project project) throws Exception {
        projectDao.delete(project);
    }
}
