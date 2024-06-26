package com.kalosha.lab.lab_1_web_dev.service;

import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exception.ServiceException;

import java.util.List;

public interface ProjectService {
    public Project addProject(Project project) throws ServiceException;

    public Project getProjectById(int id) throws ServiceException;

    public List<Project> getProjectsByUserId(int userId) throws ServiceException;

    public void updateProject(Project project) throws ServiceException;

    public void deleteProject(Project project) throws ServiceException;

    public void updatePhotoFilename(int projectId, String photoFilename) throws ServiceException;
}
