package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.DbHandler;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;
import university.homework.state.Worker;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ShowTableRowsCommand implements Command {
    private final TableSelectionSupport tableSelectionSupport;
    private final WorkersDao workersDao;

    public ShowTableRowsCommand(DbHandler dbHandler, WorkersDao workersDao) {
        this.tableSelectionSupport = new TableSelectionSupport(dbHandler);
        this.workersDao = workersDao;
    }

    @Override
    public String getName() {
        return "Show table rows";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableName = tableSelectionSupport.getSelectedTable(context);

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
