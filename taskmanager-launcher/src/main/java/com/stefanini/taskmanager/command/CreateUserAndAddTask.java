package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.UserService;

import java.util.regex.Matcher;

public class CreateUserAndAddTask extends BaseCommand {
    private final UserService userService;

    public CreateUserAndAddTask(String[] args, UserService userService) {
        super(args);
        this.userService = userService;
    }

    @Override
    protected String getPatternString() {
        return "^-createUserAndAddTask -fn='(?<firstName>\\w+)' -ln='(?<lastName>\\w+)' -un='(?<userName>\\w+)' -tt='(?<taskTitle>[\\w\\s]+)' -td='(?<taskDescription>[\\w\\s]+)'$";
    }

    @Override
    protected void command(Matcher matcher) throws ServiceException {
        String firstName = matcher.group("firstName");
        String lastName = matcher.group("lastName");
        String userName = matcher.group("userName");

        String taskTitle = matcher.group("taskTitle");
        String taskDescription = matcher.group("taskDescription");

        userService.createUserAndAddTask(firstName, lastName, userName, taskTitle, taskDescription);
    }
}
