package com.stefanini.taskmanager.utils;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtils {

    public Session openSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public Session openTransactionSession() {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        return session;
    }

    public void closeSession(Session session) {
        session.close();
    }

    public void closeTransactionSession(Session session) {
        Transaction transaction = session.getTransaction();
        transaction.commit();
        closeSession();
    }
}
