package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T> {
    public abstract List<T> findAll() throws DaoException;

    public abstract T save(T entity) throws DaoException;

    public abstract T update(T entity) throws DaoException;

    public abstract void delete(T entity) throws DaoException;
}
