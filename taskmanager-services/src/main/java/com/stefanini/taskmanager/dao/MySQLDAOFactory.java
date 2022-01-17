package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

import java.sql.SQLException;

public class MySQLDAOFactory implements AbstractDAOFactory {

    private static MySQLDAOFactory instance;

    /**
     * A Singleton
     */
    private MySQLDAOFactory() {
    }

    public static MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

    @Override
    public AbstractDAO<User> getUserDAO() throws SQLException {
        return new UserDAO();
    }

    @Override
    public AbstractDAO<Task> getTaskDAO() throws SQLException {
        return new TaskDAO();
    }

}
