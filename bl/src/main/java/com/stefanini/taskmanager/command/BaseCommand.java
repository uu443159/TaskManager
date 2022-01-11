package com.stefanini.taskmanager.command.command;

import com.stefanini.taskmanager.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseCommand implements AbstractCommand {
    protected String arguments;

    public BaseCommand(String[] args) {
        this.arguments = String.join(" ", args).trim();
    }

    @Override
    public void execute() throws ServiceException {
        Pattern pattern = Pattern.compile(getPatternString());
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.matches()) {
            command(matcher);
        } else {
            throw new IllegalArgumentException("Incorect command pattern");
        }
    }

    protected abstract String getPatternString();

    protected abstract void command(Matcher matcher) throws ServiceException;
}

