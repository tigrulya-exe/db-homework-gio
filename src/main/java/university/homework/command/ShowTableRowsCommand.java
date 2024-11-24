package university.homework.command;

import java.sql.SQLException;
import java.util.stream.Collectors;
import university.homework.command.result.CommandResult;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;
import university.homework.state.Worker;

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
        context.commandOutputRenderer().askUser("Enter table name: ");
        String tableName = context.userInputReader().next();

        try {
            String workers = workersDao.getWorkers(tableName)
                .stream()
                .map(Worker::toString)
                .collect(Collectors.joining("\n"));
            return CommandResult.success(workers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
