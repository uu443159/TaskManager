package com.stefanini.taskmanager.dao.impl;

import com.stefanini.taskmanager.annotations.SendEmail;
import com.stefanini.taskmanager.dao.TaskDAO;
import com.stefanini.taskmanager.entity.Task;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDAOImpl extends GenericDAOImpl<Task> implements TaskDAO {

    @Override
    public List<Task> getUserTasks(String name) {
        List<Task> foundTaskList = null;

        try {
            openSession();

            List<Task> taskList = session.createQuery(getCriteriaQuery()).getResultList();

            foundTaskList = taskList.stream()
                    .filter(task ->
                            task.getUserName().equals(name))
                    .collect(Collectors.toList());

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                closeSession();
            }
        }

        return foundTaskList;
    }

    @SendEmail(to = "irina.chakicheva.ext@extendaretail.com", subject = "New task", event = SendEmail.EventType.TASK_CREATED)
    @Override
    public Task addTaskToUser(Task task) {
        return create(task);
    }

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    protected Task getEntityById(List<Task> taskList, long id) {
        return taskList.stream()
                .filter(task -> task.getId() == id)
                .findAny()
                .orElse(null);
    }

}
