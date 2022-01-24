package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class TaskDAO extends BaseDAO<Task>{

    @Override
    protected Query getAllQuery(Session session) {
        return session.createQuery("FROM Task");
    }

    @Override
    public Query getByNameQuery(String name, Session session) {
        Query getByNameQuery = session.createQuery("FROM Task WHERE userName= :name");
        getByNameQuery.setParameter("name", name);

        return getByNameQuery;
    }

}
