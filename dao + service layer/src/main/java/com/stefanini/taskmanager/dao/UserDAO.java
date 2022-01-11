package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO<User> {

    /**
     * Creates a new object 'UserDAO' for the given connection to work with the 'user' database table.
     * @param connection created database connection
     */
    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getAllStatement() throws SQLException {
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
}
