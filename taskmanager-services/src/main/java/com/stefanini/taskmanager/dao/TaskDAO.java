package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDAO extends BaseDAO<Task>{

    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    Connection connection = dbConnection.openConnection();

    public TaskDAO() throws SQLException {
    }

    @Override
    protected PreparedStatement getAllStatement() throws SQLException {
        return connection.prepareStatement("SELECT * FROM user;");
    }

    @Override
    public PreparedStatement getByNameStatement(String name) throws SQLException {
        PreparedStatement getByNameStatement = connection.prepareStatement("SELECT * FROM task WHERE username = ?;");

        getByNameStatement.setString(1, name);

        return getByNameStatement;
    }

    @Override
    protected PreparedStatement getInsertStatement(Task task) throws SQLException {
        PreparedStatement addStatement = connection.prepareStatement("INSERT INTO task (username, title, description) VALUES (?,?,?);");

        addStatement.setString(1, task.getUserName());
        addStatement.setString(2, task.getTitle());
        addStatement.setString(3, task.getDescription());

        return addStatement;
    }

    @Override
    protected Task populateEntity(ResultSet resultSet) throws SQLException {
        Task task = new Task();

        task.setUserName(resultSet.getString("userName"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));

        return task;
    }
}
