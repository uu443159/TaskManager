package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.AbstractDAO;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private AbstractDAO<Task> taskDAO;

    public TaskServiceImpl(AbstractDAO<Task> taskDAO) {
        this.taskDAO = taskDAO;
    }

    private static final Logger logger = LogManager.getLogger (UserServiceImpl.class);

    /**
     * Creates a model of database table 'task' with given parameters
     * @param userName    Unique user's name
     * @param title       Task title
     * @param description Task description
     */
    @Override
    public void addTask(String userName, String title, String description) throws ServiceException {
        Task task = new Task();

        task.setUserName(userName);
        task.setTitle(title);
        task.setDescription(description);

        try {
            taskDAO.insert(task);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Shows the list of user's task(s) from 'task' database table
     */
    @Override
    public void showUsersTask(String userName) throws ServiceException {
        List<Task> taskListByUserName = new ArrayList<>();
        List<Task> taskList = null;

        try {
            taskList = taskDAO.getAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }

        for (Task task : taskList) {
            if (task.getUserName().equals(userName)) {
                taskListByUserName.add(task);
            }
        }

        System.out.printf("%s task(s): \n", userName);
        System.out.println(taskListByUserName);
    }
}
