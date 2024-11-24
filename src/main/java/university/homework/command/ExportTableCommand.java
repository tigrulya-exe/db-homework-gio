package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.DbExporter;
import university.homework.executor.ExecutionContext;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class ExportTableCommand implements Command {
    private final DbExporter dbExporter;

    public ExportTableCommand(DbExporter dbExporter) {
        this.dbExporter = dbExporter;
    }

    @Override
    public String getName() {
        return "Export table to xlsx file";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableName = context.userInputReader().next();

            dbExporter.exportTable(tableName);
            return CommandResult.success("Table " + tableName + " successfully exported");
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError(readerException.getMessage());
        } catch (SQLException exception) {
            return CommandResult.userError("Error exporting table: " + exception.getMessage());
        }
    }
}
