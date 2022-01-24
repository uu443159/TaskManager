package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T> {

    /**
     * Return all the entries from database table
     * @return The list of elements
     */
    List<T> getAll() throws SQLException;

    List<T> getByName(String name) throws SQLException;

    /**
     * Add a new entry to the database table
     * @param entity Name of database table model
     * @return Number of rows have been added
     */
    void insert(T entity) throws SQLException;
}
