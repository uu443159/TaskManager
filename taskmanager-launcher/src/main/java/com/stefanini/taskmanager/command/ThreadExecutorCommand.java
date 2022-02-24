package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.threadpool.MyThreadPoolExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

public class ThreadExecutorCommand extends BaseCommand {
    private static final Logger logger = LogManager.getLogger(ThreadExecutorCommand.class);

    private final MyThreadPoolExecutor threadPoolExecutor;

    public ThreadExecutorCommand(String[] args, MyThreadPoolExecutor threadPoolExecutor) {
        super(args);
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    protected String getPatternString() {
        return "^-insertData$";
    }

    @Override
    protected void command(Matcher matcher) throws ServiceException {

        System.out.println("Please enter username, first name, last name, task title and task description separated by ;");
        String[] tokens = new String[5];

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            {
                tokens = line.split(";");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

//        String[] mock = {"1", "2", "3", "4", "5"};
//        tokens = mock;

        String firstName = tokens[0];
        String lastName = tokens[1];
        String userName = tokens[2];

        String title = tokens[3];
        String description = tokens[4];

        threadPoolExecutor.execute(firstName, lastName, userName, title, description);
    }

}
