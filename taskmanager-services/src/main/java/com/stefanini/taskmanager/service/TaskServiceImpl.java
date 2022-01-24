package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.AbstractDAO;
import com.stefanini.taskmanager.dao.MySQLDAOFactory;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private MySQLDAOFactory daoFactory = MySQLDAOFactory.getInstance();
    private AbstractDAO<Task> taskDAO;

    {
        try {
            taskDAO = daoFactory.getTaskDAO();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static final Logger logger = LogManager.getLogger (TaskServiceImpl.class);

    @sendemail
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

    @Override
    public void showUsersTask(String userName) throws ServiceException {
        List<Task> taskList;

        try {
            taskList = taskDAO.getByName(userName);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(),e);
        }

        System.out.printf("%s task(s): \n", userName);
        System.out.println(taskList);
    }
}
