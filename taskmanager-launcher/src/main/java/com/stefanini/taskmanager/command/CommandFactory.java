package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.TaskServiceImpl;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.UserServiceImpl;

import java.sql.SQLException;

public class CommandFactory implements AbstractCommandFactory{

    private static CommandFactory instance;

    private UserService userService;
    private TaskService taskService;

    private CommandFactory() throws SQLException {
        userService = new UserServiceImpl();
        taskService = new TaskServiceImpl();
    }

    public static CommandFactory getInstance() throws SQLException {
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
            case "-createUserAndAddTask":
                command = new CreateUserAndAddTask(args, userService);
                break;
            default:
                throw new IllegalArgumentException("Incorrect command");
        }

        return command;
    }
}
