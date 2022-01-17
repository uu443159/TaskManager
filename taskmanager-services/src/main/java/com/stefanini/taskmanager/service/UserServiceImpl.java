package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.dao.AbstractDAO;
import com.stefanini.taskmanager.dao.MySQLDAOFactory;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private MySQLDAOFactory daoFactory = MySQLDAOFactory.getInstance();
    private AbstractDAO<User> userDAO;

    {
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static final Logger logger = LogManager.getLogger (UserServiceImpl.class);

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

    @Override
    public void showAllUsers() throws ServiceException {
        User user = new User();
        List<User> userList;

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
