package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface AbstractDAOFactory {
    Connection openConnection() throws SQLException;

    void closeConnection() throws SQLException;

    AbstractDAO<User> getUserDAO() throws SQLException;

    AbstractDAO<Task> getTaskDAO() throws SQLException;
}
