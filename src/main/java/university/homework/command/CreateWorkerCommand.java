package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;
import university.homework.state.Worker;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class CreateWorkerCommand implements Command {
    private final WorkersDao workersDao;

    public CreateWorkerCommand(WorkersDao workersDao) {
        this.workersDao = workersDao;
    }

    @Override
    public String getName() {
        return "Save worker to db";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            context.commandOutputRenderer().askUser("Enter table name: ");
            String tableName = context.userInputReader().next();

            Worker worker = new Worker();
            context.commandOutputRenderer().askUser("Enter name: ");
            worker.setName(context.userInputReader().next());

            context.commandOutputRenderer().askUser("Enter age: ");
            worker.setAge(context.userInputReader().nextInt());

            context.commandOutputRenderer().askUser("Enter salary: ");
            worker.setSalary(context.userInputReader().nextInt());

            workersDao.saveWorker(tableName, worker);

            return CommandResult.success("Worker successfully saved: " + worker);
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError(readerException.getMessage());
        } catch (SQLException sqlException) {
            return CommandResult.userError("Error saving worker: " + sqlException.getMessage());
        }
    }
}
