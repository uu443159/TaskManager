package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface AbstractDAOFactory {

    /**
     * Gets a data access object to work with database table 'user'
     * @return A data access object 'UserDAO'
     */
    AbstractDAO<User> getUserDAO() throws SQLException;

    /**
     * Gets a data access object to work with database table 'task'
     * @return A data access object 'TaskDAO'
     */
    AbstractDAO<Task> getTaskDAO() throws SQLException;
}
