import com.stefanini.taskmanager.command.AbstractCommand;
import com.stefanini.taskmanager.command.AbstractCommandFactory;
import com.stefanini.taskmanager.command.CommandFactory;
import com.stefanini.taskmanager.exception.ServiceException;

public class Main {
    public static void main(String[] args) {

        AbstractCommandFactory commandFactory = CommandFactory.getInstance();

/*
        args = new String[1];
        args[0] = "-showAllUsers";
*/
/*
        args = new String[4];
        args[0] = "-createUser";
        args[1] = "-fn='Ivanov'";
        args[2] = "-ln='Alex'";
        args[3] = "-un='user4'";
*/

/*
        args = new String[2];
        args[0] = "-showTasks";
        args[1] = "-un='user1'";
*/
/*
        args = new String[4];
        args[0] = "-addTask";
        args[1] = "-un='user4'";
        args[2] = "-tt='Task1'";
        args[3] = "-td='do smth 1'";
*/

        AbstractCommand command = commandFactory.getCommand(args);
        try {
            command.execute();
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}

