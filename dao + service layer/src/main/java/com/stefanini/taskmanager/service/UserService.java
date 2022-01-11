package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.exception.ServiceException;

public interface UserService {
    void createUser(String firstName, String lastName, String userName) throws ServiceException;

    void showAllUsers() throws ServiceException;
}
