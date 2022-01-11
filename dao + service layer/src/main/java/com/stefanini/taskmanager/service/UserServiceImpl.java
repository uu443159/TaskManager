package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.AbstractDAO;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private AbstractDAO<User> userDAO;

    public UserServiceImpl(AbstractDAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    private static final Logger logger = LogManager.getLogger (UserServiceImpl.class);

    /**
     * Creates a model of database table 'user' with given parameters
     * @param firstName First name of the new user
     * @param lastName  Last name of the new user
     * @param userName  Unique name of the new user
     */
    @Override
    public void createUser(String firstName, String lastName, String userName) throws ServiceException {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        try {
            userDAO.insert(user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(),e);
        }
    }

    /**
     * Shows the list of users from 'user' database table
     */
    @Override
    public void showAllUsers() throws ServiceException {
        List<User> userList = null;

        try {
            userList = userDAO.getAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(),e);
        }

        System.out.println("List of all users: ");
        System.out.println(userList);
    }
}
