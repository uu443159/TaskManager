package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.db_connection.DatabaseConnection;
import com.stefanini.taskmanager.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends DatabaseConnection implements UserDAO {

     Connection connection;

    @Override
    public List<User> showAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        Statement statement = null;
        String query = "SELECT * FROM user";

        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setUserName(resultSet.getString("userName"));

            userList.add(user);
        }

        if (statement != null) {
            statement.close();
        };

        if (connection != null) {
            connection.close();
        };

            return userList;
        }


        @Override
        public void createUser (User user) throws SQLException {
            PreparedStatement preparedStatement = null;
            String query = "INSERT INTO user (firstname, lastname, username) VALUES (?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());

            preparedStatement.executeUpdate();

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            ;

            if (connection != null) {
                connection.close();

            }
        }
    }
