package university.homework.command;

import university.homework.db.DbHandler;
import university.homework.executor.ExecutionContext;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class TableSelectionSupport {
    private final DbHandler dbHandler;

    public TableSelectionSupport(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public String getSelectedTable(ExecutionContext context) throws SQLException, NoSuchElementException {
        List<String> dbTableNames = dbHandler.getDbTableNames();

        String tableNamesString = String.join(", ", dbHandler.getDbTableNames());
        context.commandOutputRenderer().render("Available table names: " + tableNamesString + "\n");

        context.commandOutputRenderer().render("Enter table name: ");
        String tableName = context.userInputReader().next();

        if (!dbTableNames.contains(tableName)) {
            throw new NoSuchElementException("Table not found: " + tableName);
        }

        return tableName;
    }
}
