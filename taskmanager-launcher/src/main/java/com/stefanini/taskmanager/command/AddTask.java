package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.TaskService;

import java.util.regex.Matcher;

public class AddTask extends BaseCommand {
    private TaskService taskService;

    public AddTask(String[] args, TaskService taskService) {
        super(args);
        this.taskService = taskService;
    }

    @Override
    protected String getPatternString() {
        return "^-addTask -un='(?<userName>\\w+)' -tt='(?<taskTitle>[\\w\\s]+)' -td='(?<taskDescription>[\\w\\s]+)'$";
    }

    @Override
    protected void command(Matcher matcher) throws ServiceException {
        String userName = matcher.group("userName");
        String title = matcher.group("taskTitle");
        String description = matcher.group("taskDescription");

        taskService.addTask(userName, title, description);
    }
}