package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoException;

import java.util.List;

public interface ProjectDao {
    public abstract List<Project> findAll() throws DaoException;

    public abstract Project create(Project entity) throws DaoException;

    public abstract Project update(Project entity) throws DaoException;

    public abstract void delete(Project entity) throws DaoException;

    Project getProjectById(int id) throws DaoException;

    List<Project> getProjectsByUserId(int userId) throws DaoException;

}
