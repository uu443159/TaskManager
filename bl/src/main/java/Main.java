import com.stefanini.taskmanager.dao.AbstractDAO;
import com.stefanini.taskmanager.dao.MySQLDAOFactory;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.exception.ServiceException;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.TaskServiceImpl;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {

            MySQLDAOFactory daoFactory = MySQLDAOFactory.getInstance();

            AbstractDAO<User> userDAO = daoFactory.getUserDAO();
            UserService userService = new UserServiceImpl(userDAO);

            AbstractDAO<Task> taskDAO = daoFactory.getTaskDAO();
            TaskService taskService = new TaskServiceImpl(taskDAO);

            AbstractCommand inputCommand;


            args = new String[1];
            args[0] = "-showAllUsers";

/*
            args = new String[4];
            args[0] = "-createUser";
            args[1] = "-fn='Ivanov'";
            args[2] = "-ln='Ivan'";
            args[3] = "-un='user1'";
            */


            switch (args[0]) {
                case "-createUser":
                    inputCommand = new CreateUser(args, userService);
                    break;
                case "-addTask":
                    inputCommand = new AddTask(args, taskService);
                    break;
                case "-showAllUsers":
                    inputCommand = new ShowAllUsers(args, userService);
                    break;
                case "-showTasks":
                    inputCommand = new ShowUsersTask(args, taskService);
                default:
                    throw new IllegalArgumentException("Incorrect command");

            }

            inputCommand.execute();

            daoFactory.closeConnection();

        } catch (SQLException | ServiceException | IllegalArgumentException e) {
            System.err.println("Caught exception");
            e.printStackTrace();
        }
    }
}
