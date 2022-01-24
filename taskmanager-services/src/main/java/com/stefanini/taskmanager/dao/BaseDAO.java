package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.utils.HibernateSessionFactoryUtil;
import com.stefanini.taskmanager.utils.SessionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseDAO<T> implements AbstractDAO<T> {

    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private SessionUtils sessionUtil = new SessionUtils();

    private static final Logger logger = LogManager.getLogger(BaseDAO.class);

    @Override
    public List<T> getAll()  {
        Session session = sessionUtil.openSession();

        Query query = getAllQuery(session);

        List<T> entityList = query.list();

        sessionUtil.closeSession(session);

        return entityList;
    }

    @Override
    public List<T> getByName(String name) {
        Session session = sessionUtil.openSession();

        Query query = getByNameQuery(name, session);

        List<T> entityList = query.list();

        sessionUtil.closeSession(session);

        return entityList;
    }

    @Override
    public void insert(T entity) {
        Session session = sessionUtil.openTransactionSession();

        session.save(entity);

        sessionUtil.closeTransactionSession(session);

    }

    protected abstract Query getAllQuery(Session session);

    protected abstract Query getByNameQuery(String name, Session session);

}
