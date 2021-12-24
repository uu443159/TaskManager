package com.stefanini.taskmanager.command;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTask extends Command {

    public AddTask(String[] args) {
        super(args);
    }

    @Override
    public String getPatternString() {
        return "^-addTask -un='(?<userName>\\w+)' -tt='(?<taskTitle>[\\w\\s]+)' -td='(?<taskDescription>[\\w\\s]+)'$";
    }

    @Override
    public void doCommand(Matcher matcher) throws SQLException {
        String userName = matcher.group("userName");
        String title = matcher.group("taskTitle");
        String description = matcher.group("taskDescription");

    }
}
