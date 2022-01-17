package com.stefanini.taskmanager.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection connection;

    Properties properties = new Properties();

    {
        try {
            InputStream inputStream = new FileInputStream ("taskmanager-services/src/main/resources/loginMySQL.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String DB_URL = properties.getProperty("MySQL_DB_URL");
    String DB_USERNAME = properties.getProperty("MySQL_DB_USERNAME");
    String DB_PASSWORD = properties.getProperty("MySQL_DB_PASSWORD");

    /**
     * A singleton
     */
    private DatabaseConnection() throws SQLException{
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * Return a DatabaseConnection object that implements connection to tha database
     * @return DatabaseConnection instance
     * @throws SQLException
     */
     public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Attempts to establish a connection to the given database URL
     * @return a connection to the URL
     */
    public Connection openConnection() {
        return connection;
    }

}
