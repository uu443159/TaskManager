package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    //select
    List<User> showAllUsers() throws SQLException;

    //insert
    void createUser(User user) throws SQLException;

}
