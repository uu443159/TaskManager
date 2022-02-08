package com.stefanini.taskmanager;

import com.stefanini.taskmanager.command.AbstractCommand;
import com.stefanini.taskmanager.command.AbstractCommandFactory;
import com.stefanini.taskmanager.command.CommandFactory;
import com.stefanini.taskmanager.exception.ServiceException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        AbstractCommandFactory commandFactory = CommandFactory.getInstance();


        args = new String[1];
        args[0] = "-showAllUsers";


//        args = new String[4];
//        args[0] = "-createUser";
//        args[1] = "-fn='Maximov'";
//        args[2] = "-ln='Max'";
//        args[3] = "-un='maxik'";



//        args = new String[2];
//        args[0] = "-showTasks";
//        args[1] = "-un='sidorov2'";



//
//        args = new String[4];
//        args[0] = "-addTask";
//        args[1] = "-un='petrov'";
//        args[2] = "-tt='Task1'";
//        args[3] = "-td='do smth 1'";


//        args = new String[6];
//        args[0] = "-createUserAndAddTask";
//        args[1] = "-fn='Sidorov'";
//        args[2] = "-ln='Sasha'";
//        args[3] = "-un='sidorov10'";
//        args[4] = "-tt='Task1'";
//        args[5] = "-td='do smth 1'";


        AbstractCommand command = commandFactory.getCommand(args);
        try {
            command.execute();
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}

