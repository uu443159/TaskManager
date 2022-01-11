package com.stefanini.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T> {

    List<T> getAll() throws SQLException;

    int insert(T entity) throws SQLException;
}
