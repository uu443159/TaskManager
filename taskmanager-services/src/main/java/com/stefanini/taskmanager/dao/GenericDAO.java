package com.stefanini.taskmanager.dao;

import java.util.List;

public interface GenericDAO<T> {

    T findById(long id);

    List<T> findAll();

    void delete(T entity);

    void update(T entity);

    T create (T entity);

}
