package com.stefanini.taskmanager.threadpool;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolExecutor {
    private static final Logger logger = LogManager.getLogger(MyThreadPoolExecutor.class);

    private final UserService userService;
    private final TaskService taskService;

    public MyThreadPoolExecutor(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    public void execute(String firstName, String lastName, String userName, String title, String description) {

        Runnable createUser = () -> {
            try {
                userService.createUser(firstName, lastName, userName);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            logger.info(Thread.currentThread().getName() + " is running...");
        };

        Runnable addTask = () -> {
            try {
                taskService.addTask(userName, title, description);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            logger.info(Thread.currentThread().getName() + " is running...");
        };

        Runnable showAllUsers = () -> {
            try {
                userService.showAllUsers();
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            logger.info(Thread.currentThread().getName() + " is running...");

        };

        Runnable showUsersTask = () -> {
            try {
                taskService.showUsersTask(userName);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            logger.info(Thread.currentThread().getName() + " is running...");
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            executor.submit(createUser).get();
            executor.submit(addTask).get();
            executor.execute(showAllUsers);
            executor.submit(showUsersTask).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }

        executor.shutdown();
        logger.info("Finished all threads");
    }
}
