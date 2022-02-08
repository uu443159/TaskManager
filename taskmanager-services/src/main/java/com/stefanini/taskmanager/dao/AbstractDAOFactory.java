package com.stefanini.taskmanager.dao;

import java.sql.SQLException;

public interface AbstractDAOFactory {

    /**
     * Gets a data access object to work with database table 'user'
     * @return A data access object 'UserDAO'
     */
    UserDAO getUserDAO() throws SQLException;

    /**
     * Gets a data access object to work with database table 'task'
     * @return A data access object 'TaskDAO'
     */
    TaskDAO getTaskDAO() throws SQLException;
}
