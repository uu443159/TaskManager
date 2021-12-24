package com.stefanini.taskmanager.command;


import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUser extends Command {
    public CreateUser(String[] args) {
        super(args);
    }

    @Override
    public String getPatternString() {
        return "^-createUser -fn='(?<firstName>\\w+)' -ln='(?<lastName>\\w+)' -un='(?<userName>\\w+)'$";
    }

    @Override
    public void doCommand(Matcher matcher) throws SQLException {
        String firstName = matcher.group("firstName");
        String lastName = matcher.group("lastName");
        String userName = matcher.group("userName");

    }
}
