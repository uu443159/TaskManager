package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    List<User> getAllUsers ();

    User createUser(User user);

}
