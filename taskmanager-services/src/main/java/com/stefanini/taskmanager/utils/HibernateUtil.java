package com.stefanini.taskmanager.utils;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static final String PROPERTIES_FILENAME = "hibernate.properties";

    private static Properties properties;
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    public static void buildSessionFactory() {
        try {
            properties = PropertiesLoader.getProperties(PROPERTIES_FILENAME);
            configuration = new Configuration().setProperties(properties);

            sessionFactory = configuration.addAnnotatedClass(User.class).addAnnotatedClass(Task.class).buildSessionFactory();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }
}

