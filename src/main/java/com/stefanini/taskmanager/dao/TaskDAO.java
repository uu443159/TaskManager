package com.stefanini.taskmanager.dao;

import com.stefanini.taskmanager.entity.Task;

import java.util.List;

public interface TaskDAO {

    //select
    List<Task> showUsersTask();

    //insert
    void addTask(Task task);

}