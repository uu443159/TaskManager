package com.stefanini.taskmanager.command;


import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.UserService;

import java.util.regex.Matcher;

public class CreateUser extends BaseCommand {
    private final UserService userService;

    public CreateUser(String[] args, UserService userService) {
        super(args);
        this.userService = userService;
    }

    @Override
    protected String getPatternString() {
        return "^-createUser -fn='(?<firstName>\\w+)' -ln='(?<lastName>\\w+)' -un='(?<userName>\\w+)'$";
    }

    @Override
    protected void command(Matcher matcher) throws ServiceException {
        String firstName = matcher.group("firstName");
        String lastName = matcher.group("lastName");
        String userName = matcher.group("userName");

        userService.createUser(firstName, lastName, userName);
    }
}
