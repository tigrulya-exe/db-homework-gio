package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.WorkersDao;
import university.homework.executor.ExecutionContext;

import java.sql.SQLException;
import java.util.NoSuchElementException;

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
            context.commandOutputRenderer().render("Enter table name: ");
            String tableName = context.userInputReader().next();

            workersDao.createNewTable(tableName);
            return CommandResult.success("Table " + tableName + " successfully created");
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError(readerException.getMessage());
        } catch (SQLException exception) {
            return CommandResult.userError("Error creating table: " + exception.getMessage());
        }
    }
}
