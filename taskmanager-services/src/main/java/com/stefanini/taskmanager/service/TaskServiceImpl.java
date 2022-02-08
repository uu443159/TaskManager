package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.MySQLDAOFactory;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private static final String TO = "irina.chakicheva.ext@extendaretail.com";
    private static final String SUBJECT = "Information about new user and/or task";

    private static final Logger logger = LogManager.getLogger (TaskServiceImpl.class);

    private final TaskDAO taskDAO;

    public TaskServiceImpl() throws SQLException {
        MySQLDAOFactory daoFactory = MySQLDAOFactory.getInstance();
        taskDAO = daoFactory.getTaskDAO();
    }

    @Override
    public void addTask(String userName, String title, String description) throws ServiceException {
        Task task = new Task();

        task.setUserName(userName);
        task.setTitle(title);
        task.setDescription(description);

        taskDAO.addTaskToUser(task);

    }

    @Override
    public void showUsersTask(String userName) throws ServiceException {
        List<Task> taskList;

        taskList = taskDAO.getUserTasks(userName);

        System.out.printf("%s task(s): \n", userName);
        System.out.println(taskList);
    }
}
