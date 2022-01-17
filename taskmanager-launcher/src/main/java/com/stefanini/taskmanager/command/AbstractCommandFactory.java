package com.stefanini.taskmanager.command;

public interface AbstractCommandFactory {
    AbstractCommand getCommand(String[] args);
}
