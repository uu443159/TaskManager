package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.utils.DatabaseConnection;

import java.sql.*;

public class UserDAO extends BaseDAO<User> {

    DatabaseConnection db_connection = DatabaseConnection.getInstance();
    Connection connection = db_connection.openConnection();

    @Override
    protected PreparedStatement getAllStatement(User user) throws SQLException {
        return connection.prepareStatement("SELECT * FROM user;");
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

    public UserDAO() throws SQLException {
    }
}
