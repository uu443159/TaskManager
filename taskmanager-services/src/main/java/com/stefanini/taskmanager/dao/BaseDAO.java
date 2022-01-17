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

    private static final Logger logger = LogManager.getLogger(BaseDAO.class);

    @Override
    public List<T> getAll(T entity) throws SQLException {

        List<T> entityList = new ArrayList<>();

        ResultSet resultSet = getAllStatement(entity).executeQuery();

        while (resultSet.next()) {
            entity = populateEntity(resultSet);
            entityList.add(entity);
        }

        return entityList;
    }

    @Override
    public int insert(T entity) throws SQLException{
        int rowCount = getInsertStatement(entity).executeUpdate();
        if(rowCount == 1) {
            logger.info("The new entry has been added to the database");
        }
        return rowCount;
    }

    protected abstract PreparedStatement getAllStatement(T entity) throws SQLException;

    protected abstract PreparedStatement getInsertStatement(T entity) throws SQLException;

    protected abstract T populateEntity(ResultSet resultSet) throws SQLException;

}