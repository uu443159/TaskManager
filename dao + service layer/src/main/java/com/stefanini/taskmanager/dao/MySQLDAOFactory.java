package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory implements AbstractDAOFactory {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/taskManager";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private Connection connection;

    /**
     * A singleton
     */
    public static MySQLDAOFactory instance;

    public static MySQLDAOFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }

        return instance;
    }

    /**
     * Gets a data access object 'UserDAO' to work with database
     */
    @Override
    public AbstractDAO<User> getUserDAO() throws SQLException {
        return new UserDAO(connection);
    }

    /**
     * Gets a data access object 'TaskDAO' to work with database
     */
    @Override
    public AbstractDAO<Task> getTaskDAO() throws SQLException {
        return new TaskDAO(connection);
    }

    /**
     * Closes the connection with database
     */
    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     * Opens the connection with database
     * @return A connection to the database URL
     */
    @Override
    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public MySQLDAOFactory() throws SQLException {
        connection = openConnection();
    }
}
