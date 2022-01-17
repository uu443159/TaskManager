package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.exception.ServiceException;

public interface TaskService {
    /**
     * Creates a model of database table 'task' with given parameters
     * @param userName    Unique user's name
     * @param title       Task title
     * @param description Task description
     */
    void addTask (String userName, String title, String description) throws ServiceException;

    /**
     * Shows the list of user's task(s) from 'task' database table
     */
    void showUsersTask(String userName) throws ServiceException;
}
