package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.dao.GenericDAO;
import com.stefanini.taskmanager.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    protected Session session;
    protected Transaction transaction;
    protected Query query;

    protected void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void closeSession() {
        session.close();
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T findById(long id) {
        T entity = null;

        try {
            openSession();
            query = session.createQuery("FROM " + getEntityClass().getSimpleName() + " WHERE id= :" + id);
            query.setParameter("id", id);

            entity = (T) query.uniqueResult();

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
            query = session.createQuery("FROM " + getEntityClass().getSimpleName());

            entityList = query.list();

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
}
