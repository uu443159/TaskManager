package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.TaskServiceImpl;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.UserServiceImpl;

public class CommandFactory implements AbstractCommandFactory{

    private static CommandFactory instance;

    private UserService userService;
    private TaskService taskService;

    private CommandFactory() {
        userService = new UserServiceImpl();
        taskService = new TaskServiceImpl();
    }

    public static CommandFactory getInstance() {
        if(instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    @Override
    public AbstractCommand getCommand (String[] args) {
        AbstractCommand command;

        switch (args[0]) {
            case "-createUser":
                command = new CreateUser(args, userService);
                break;
            case "-addTask":
                command = new AddTask(args, taskService);
                break;
            case "-showAllUsers":
                command = new ShowAllUsers(args, userService);
                break;
            case "-showTasks":
                command = new ShowUsersTask(args, taskService);
                break;
            default:
                throw new IllegalArgumentException("Incorrect command");
        }

        return command;
    }
}
