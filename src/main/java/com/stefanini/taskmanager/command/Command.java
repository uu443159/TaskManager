package com.stefanini.taskmanager.command;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command implements AbstractComand {

    public String arguments;
    public Command(String[] args) {
        this.arguments = String.join(" ", args).trim();
    }

    public abstract String getPatternString();
    public abstract void doCommand(Matcher matcher) throws SQLException;

    @Override
    public void toDo() throws SQLException {
        Pattern pattern = Pattern.compile(getPatternString());
        Matcher matcher = pattern.matcher(arguments);

        if (matcher.matches()) {
            doCommand(matcher);
        } else {
            throw new IllegalArgumentException("Incorrect command");
        }

    }


}
