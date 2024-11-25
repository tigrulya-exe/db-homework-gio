package university.homework.command;

import university.homework.command.result.CommandResult;
import university.homework.db.DbExporter;
import university.homework.db.DbHandler;
import university.homework.executor.ExecutionContext;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class ExportTableCommand implements Command {
    private final DbExporter dbExporter;
    private final TableSelectionSupport tableSelectionSupport;

    public ExportTableCommand(DbHandler dbHandler, DbExporter dbExporter) {
        this.tableSelectionSupport = new TableSelectionSupport(dbHandler);
        this.dbExporter = dbExporter;
    }

    @Override
    public String getName() {
        return "Export table to XLSX file";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        try {
            String tableName = tableSelectionSupport.getSelectedTable(context);

            dbExporter.exportTable(tableName);
            return CommandResult.success("Table " + tableName + " successfully exported");
        } catch (NoSuchElementException | IllegalStateException readerException) {
            return CommandResult.userError(readerException.getMessage());
        } catch (SQLException exception) {
            return CommandResult.userError("Error exporting table: " + exception.getMessage());
        }
    }
}
