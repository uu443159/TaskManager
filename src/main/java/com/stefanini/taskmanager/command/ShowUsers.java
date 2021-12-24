package com.stefanini.taskmanager.command;

import java.sql.SQLException;
import java.util.regex.Matcher;

public class ShowUsers extends Command {

    public ShowUsers(String[] args) {
        super(args);
    }

    @Override
    public String getPatternString() {
        return "^-showAllUsers$";
    }

    @Override
    public void doCommand(Matcher matcher) throws SQLException {

    }
}
