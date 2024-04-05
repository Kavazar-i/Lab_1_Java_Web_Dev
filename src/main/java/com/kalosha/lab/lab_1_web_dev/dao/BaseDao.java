package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.entity.AbstractEntity;
import com.kalosha.lab.lab_1_web_dev.exeption.DaoExeption;

import java.util.List;

public abstract class BaseDao<T> extends AbstractEntity {
    public abstract boolean insert(T entity) throws DaoExeption;
    public abstract boolean delete(T entity) throws DaoExeption;
    public abstract List<T> findAll() throws DaoExeption;
    public abstract T update(T entity) throws DaoExeption;
}
