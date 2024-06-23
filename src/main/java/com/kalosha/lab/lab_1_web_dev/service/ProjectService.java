package com.kalosha.lab.lab_1_web_dev.service;

import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exeption.ServiceException;

import java.util.List;

public interface ProjectService {
    public void addProject(Project project) throws ServiceException;

    public Project getProjectById(int id) throws ServiceException;

    public List<Project> getProjectsByUserId(int userId) throws ServiceException;

    public void updateProject(Project project) throws ServiceException;

    public void deleteProject(int id) throws ServiceException;
}
