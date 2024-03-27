package com.kalosha.lab.lab_1_web_dev.dao;

import com.kalosha.lab.lab_1_web_dev.entity.AbstractEntity;

import java.util.List;

public abstract class BaseDao<T> extends AbstractEntity {
    public abstract boolean insert(T entity);
    public abstract boolean delete(T entity);
    public abstract List<T> findAll();
    public abstract T update(T entity);
}
