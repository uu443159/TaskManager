package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.dao.impl.TaskDAOImpl;
import com.stefanini.taskmanager.dao.impl.UserDAOImpl;

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
    public UserDAO getUserDAO() throws SQLException {
        return new UserDAOImpl();
    }

    @Override
    public TaskDAO getTaskDAO() throws SQLException {
        return new TaskDAOImpl();
    }

}
