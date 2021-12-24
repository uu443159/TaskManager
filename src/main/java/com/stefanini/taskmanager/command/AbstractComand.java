package com.stefanini.taskmanager.command;

import java.sql.SQLException;

public interface AbstractComand {

    void toDo() throws SQLException;
}
