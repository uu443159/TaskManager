package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.exception.ServiceException;

public interface AbstractCommand {
    public void execute() throws ServiceException;
}