package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.exception.ServiceException;

public interface TaskService {
    void addTask (String userName, String title, String description) throws ServiceException;

    void showUsersTask(String userName) throws ServiceException;
}
