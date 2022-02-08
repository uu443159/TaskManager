package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> getUserTasks (String name);

    Task addTaskToUser(Task task);
}
