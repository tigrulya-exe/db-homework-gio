package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.DbHandler;
import university.homework.executor.ExecutionContext;

import java.sql.SQLException;

public class ShowTablesCommand implements Command {
    private final DbHandler dbHandler;

    public ShowTablesCommand(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public String getName() {
        return "Show 'Worker' table names";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableNames = String.join("\n", dbHandler.getDbTableNames());
            return CommandResult.success(tableNames);
        } catch (SQLException exception) {
            return CommandResult.userError("Error getting tables: " + exception.getMessage());
        }
    }
}
