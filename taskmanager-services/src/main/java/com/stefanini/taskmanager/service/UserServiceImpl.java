package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.MySQLDAOFactory;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger (UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl() throws SQLException {
        MySQLDAOFactory daoFactory = MySQLDAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void createUser(String firstName, String lastName, String userName) throws ServiceException {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        userDAO.createUser(user);
    }

    @Override
    public void showAllUsers() throws ServiceException {
        List<User> userList;

        userList = userDAO.getAllUsers();

        System.out.println("List of all users: ");
        System.out.println(userList);
    }

    @Override
    public void createUserAndAddTask(String firstName, String lastName, String userName, String title, String description) throws ServiceException {
        User user = new User();
        Task task = new Task();
        List<Task> taskList = new ArrayList<>();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        task.setUserName(userName);
        task.setTitle(title);
        task.setDescription(description);

        taskList.add(task);
        user.setTaskList(taskList);

        userDAO.createUser(user);

    }
}
