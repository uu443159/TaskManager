package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.annotations.SendEmail;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.entity.Task;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl extends GenericDAOImpl implements TaskDAO {

    @Override
    public List<Task> getUserTasks(String name) {
        List<Task> taskList = new ArrayList<>();

        try {
            openSession();
            query = session.createQuery("FROM Task WHERE userName= :name");
            query.setParameter("name", name);

            taskList = query.list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }

        return taskList;
    }

    @SendEmail(to = "irina.chakicheva.ext@extendaretail.com", subject = "New task", event = SendEmail.EventType.TASK_CREATED)
    @Override
    public Task addTaskToUser(Task task) {
        return (Task) create(task);
    }

    @Override
    protected Class getEntityClass() {
        return Task.class;
    }
}
