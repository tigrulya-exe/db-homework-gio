package university.homework.command;

import java.sql.SQLException;
import university.homework.executor.ExecutionContext;
import university.homework.command.result.CommandResult;
import university.homework.db.WorkersDao;

public class CreateTableCommand implements Command {
    private final WorkersDao workersDao;

    public CreateTableCommand(WorkersDao workersDao) {
        this.workersDao = workersDao;
    }

    @Override
    public String getName() {
        return "Create table";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableName = context.userInputReader().next();
            workersDao.createNewTable(tableName);
            return CommandResult.success("Table " + tableName + " created");
        } catch (SQLException exception) {
            return CommandResult.userError("Error creating table: " + exception.getMessage());
        }
    }
}
