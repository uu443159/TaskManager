package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.annotations.SendEmail;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.entity.User;

import java.util.List;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    @Override
    public List<User> getAllUsers() {
        return findAll();
    }

    @SendEmail(to = "irina.chakicheva.ext@extendaretail.com", subject = "New user", event = SendEmail.EventType.USER_CREATED)
    @Override
    public User createUser(User user) {
        return create(user);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
