package com.stefanini.taskmanager.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> implements AbstractDAO<T> {

    protected Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = LogManager.getLogger(BaseDAO.class);

    /**
     * Get all entries from the database table
     * @param <T> Type of element stored in a list
     * @return The list of elements
     */
    @Override
    public List<T> getAll() throws SQLException {

        List<T> entityList = new ArrayList<>();

        ResultSet resultSet = getAllStatement().executeQuery();

        while (resultSet.next()) {
            T entity = populateEntity(resultSet);
            entityList.add(entity);
        }

        return entityList;
    }

    /**
     * Add a new entry to the database table
     * @param T Type of element
     * @return Number of rows have been added
     */
    @Override
    public int insert(T entity) throws SQLException{
        int rowCount = getInsertStatement(entity).executeUpdate();
        if(rowCount == 1) {
            logger.info("The new entry has been added to the database");
        }
        return rowCount;
    }

    protected abstract PreparedStatement getAllStatement() throws SQLException;

    protected abstract PreparedStatement getInsertStatement(T entity) throws SQLException;

    protected abstract T populateEntity(ResultSet resultSet) throws SQLException;

}
