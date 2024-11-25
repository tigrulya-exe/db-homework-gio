package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.DbHandler;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;
import university.homework.state.Worker;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class CreateWorkerCommand implements Command {
    private final TableSelectionSupport tableSelectionSupport;
    private final WorkersDao workersDao;

    public CreateWorkerCommand(DbHandler dbHandler, WorkersDao workersDao) {
        this.tableSelectionSupport = new TableSelectionSupport(dbHandler);
        this.workersDao = workersDao;
    }

    @Override
    public String getName() {
        return "Save worker to db";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableName = tableSelectionSupport.getSelectedTable(context);

            Worker worker = new Worker();
            context.commandOutputRenderer().render("Enter worker's name: ");
            worker.setName(context.userInputReader().next());

            context.commandOutputRenderer().render("Enter worker's age: ");
            worker.setAge(context.userInputReader().nextInt());

            context.commandOutputRenderer().render("Enter worker's salary: ");
            worker.setSalary(context.userInputReader().nextInt());

            workersDao.saveWorker(tableName, worker);

            return CommandResult.success("Worker successfully saved: " + worker);
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError("Error saving worker: wrong format");
        } catch (SQLException sqlException) {
            return CommandResult.userError("Error saving worker: " + sqlException.getMessage());
        }
    }
}
