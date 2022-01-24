package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO extends BaseDAO<User> {

    @Override
    protected Query getAllQuery(Session session)  {
        return session.createQuery("FROM User");
    }

    @Override
    public Query getByNameQuery (String name, Session session) {
        Query getByNameQuery = session.createQuery("FROM User WHERE userName= :name");
        getByNameQuery.setParameter("name", name);

        return getByNameQuery;
    }
}
