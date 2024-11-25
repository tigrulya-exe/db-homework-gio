package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;
import university.homework.state.Worker;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ShowTableRowsCommand implements Command {
    private final WorkersDao workersDao;

    public ShowTableRowsCommand(WorkersDao workersDao) {
        this.workersDao = workersDao;
    }

    @Override
    public String getName() {
        return "Show table rows";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            context.commandOutputRenderer().render("Enter table name: ");
            String tableName = context.userInputReader().next();

            String workers = workersDao.getWorkers(tableName)
                    .stream()
                    .map(Worker::toString)
                    .collect(Collectors.joining("\n"));
            return CommandResult.success(workers);
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError(readerException.getMessage());
        } catch (SQLException sqlException) {
            return CommandResult.userError("Error showing table rows: " + sqlException.getMessage());
        }
    }
}
