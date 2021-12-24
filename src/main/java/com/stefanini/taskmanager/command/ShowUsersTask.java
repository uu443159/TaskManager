package com.stefanini.taskmanager.command;

import java.sql.SQLException;
import java.util.regex.Matcher;

public class ShowUsersTask extends Command {

    public ShowUsersTask(String[] args) {
        super(args);
    }

    @Override
    public String getPatternString() {
        return "^-showTasks -un='(?<userName>\\w+)'$";
    }

    @Override
    public void doCommand(Matcher matcher) throws SQLException {
        String userName = matcher.group("userName");

    }
}
