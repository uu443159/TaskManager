package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAO extends BaseDAO<Task>{

    /**
     * Creates a new object 'TaskDAO' for the given connection to work with the 'task' database table.
     * @param connection created database connection
     */
    public TaskDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getAllStatement() throws SQLException {
        return connection.prepareStatement("SELECT * FROM task;");
    }

    @Override
    protected PreparedStatement getInsertStatement(Task task) throws SQLException {
        PreparedStatement addStatement = connection.prepareStatement("INSERT INTO task (username, title, descricption) VALUES (?,?,?);");

        addStatement.setString(1,task.getUserName());
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
