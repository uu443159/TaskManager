package com.stefanini.taskmanager;


import com.stefanini.taskmanager.command.*;
import com.stefanini.taskmanager.db_connection.DatabaseConnection;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;

public class Main {
    public static void main(String[] args)  {

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        UserService userService = new UserService();
        TaskService taskService = new TaskService();

        AbstractComand inputCommand;

        switch (args[0]) {
            case "-createUser":
                inputCommand = new CreateUser(args);
                break;
            case "-addTask":
                inputCommand = new AddTask(args);
                break;
            case "-showAllUsers":
                inputCommand = new ShowUsers(args);
                break;
            case "-showTasks":
                inputCommand = new ShowUsersTask(args);

        }

        inputCommand.toDo();

    }
}
