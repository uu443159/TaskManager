package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.utils.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class UserDAO extends BaseDAO<User> {

    DatabaseConnection db_connection = DatabaseConnection.getInstance();
    Connection connection = db_connection.openConnection();

    public UserDAO() throws SQLException {
    }

    @Override
    protected PreparedStatement getAllStatement() throws SQLException {
        return connection.prepareStatement("SELECT * FROM user;");
    }

    @Override
    public PreparedStatement getByNameStatement (String name) throws SQLException {
        PreparedStatement getByNameStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?;");

        getByNameStatement.setString(1, name);

        return getByNameStatement;
    }

    @Override
    protected PreparedStatement getInsertStatement(User user) throws SQLException {
        PreparedStatement addStatement = connection.prepareStatement("INSERT INTO user (firstname, lastname, username) VALUES (?,?,?);");

        addStatement.setString(1, user.getFirstName());
        addStatement.setString(2, user.getLastName());
        addStatement.setString(3, user.getUserName());

        return addStatement;
    }

    @Override
    protected User populateEntity(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setUserName(resultSet.getString("userName"));

        return user;
    }

}
