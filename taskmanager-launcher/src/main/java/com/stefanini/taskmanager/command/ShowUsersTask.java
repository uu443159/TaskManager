package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.TaskService;

import java.util.regex.Matcher;

public class ShowUsersTask extends BaseCommand {
    private final TaskService taskService;

    public ShowUsersTask(String[] args, TaskService taskService) {
        super(args);
        this.taskService = taskService;
    }

    @Override
    public String getPatternString() {
        return "^-showTasks -un='(?<userName>\\w+)'$";
    }

    @Override
    public void command(Matcher matcher) throws ServiceException {
        String userName = matcher.group("userName");

        taskService.showUsersTask(userName);
    }
}
