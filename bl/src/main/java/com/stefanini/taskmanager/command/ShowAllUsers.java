package com.stefanini.taskmanager.command.command;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.UserService;

import java.util.regex.Matcher;

public class ShowAllUsers extends BaseCommand {
    private final UserService userService;

    public ShowAllUsers(String[] args, UserService userService) {
        super(args);
        this.userService = userService;
    }

    @Override
    protected String getPatternString() {
        return "^-showAllUsers$";
    }

    @Override
    protected void command(Matcher matcher) throws ServiceException {
        userService.showAllUsers();
    }
}
