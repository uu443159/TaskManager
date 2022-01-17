package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.exception.ServiceException;

public interface UserService {
    /**
     * Creates a model of database table 'user' with given parameters
     * @param firstName First name of the new user
     * @param lastName  Last name of the new user
     * @param userName  Unique name of the new user
     */
    void createUser(String firstName, String lastName, String userName) throws ServiceException;

    /**
     * Shows the list of users from 'user' database table
     */
    void showAllUsers() throws ServiceException;
}
