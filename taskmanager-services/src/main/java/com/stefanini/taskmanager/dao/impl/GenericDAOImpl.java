package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.GenericDAO;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    protected Session session;
    protected Transaction transaction;
    protected CriteriaQuery<T> query;

    protected void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void closeSession() {
        session.close();
    }

    protected abstract Class<T> getEntityClass();

    protected CriteriaQuery<T> getCriteriaQuery() {
        query = session.getCriteriaBuilder().createQuery(getEntityClass());
        query.from(getEntityClass());

        return query;
    }

    @Override
    public T findById(long id) {
        List<T> entityList;
        T entity = null;

        try {
            openSession();
            entityList = session.createQuery(getCriteriaQuery()).getResultList();

            entity = getEntityById(entityList, id);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        List<T> entityList = null;

        try {
            openSession();
            entityList = session.createQuery(getCriteriaQuery()).getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }

        return entityList;
    }

    @Override
    public void delete(T entity) {
        try {
            openSession();
            session.delete(entity);
            transaction.commit();

        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }
    }

    @Override
    public void update(T entity) {
        try {
            openSession();
            session.update(entity);
            transaction.commit();

        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }
    }

    @Override
    public T create(T entity) {

        try {
            openSession();
            session.save(entity);
            transaction.commit();

        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }

        return entity;
    }

    protected abstract T getEntityById(List<T> entityList, long id);
}
