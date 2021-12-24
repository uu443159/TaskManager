package com.stefanini.taskmanager.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/taskManager";

    private Connection connection;

    //Singleton
    public static DatabaseConnection instance;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }
}
