package university.homework;

import org.postgresql.ds.PGSimpleDataSource;
import university.homework.command.Command;
import university.homework.command.CreateTableCommand;
import university.homework.command.CreateWorkerCommand;
import university.homework.command.ExportTableCommand;
import university.homework.command.ShowTableRowsCommand;
import university.homework.command.ShowTablesCommand;
import university.homework.db.DbExporter;
import university.homework.db.DbHandler;
import university.homework.db.ExcelDbExporter;
import university.homework.db.WorkersDao;
import university.homework.db.postgres.PostgresDbHandler;
import university.homework.db.postgres.PostgresWorkersDao;
import university.homework.executor.CommandExecutor;
import university.homework.render.StringCommandOutputRenderer;
import university.homework.render.StringCommandRenderer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();

        CommandExecutor commandExecutor = new CommandExecutor(
                getCommands(dataSource),
                new StringCommandRenderer(System.out),
                new StringCommandOutputRenderer(System.out),
                new Scanner(System.in)
        );

        commandExecutor.run();
    }

    private static List<Command> getCommands(DataSource dataSource) {
        DbHandler dbHandler = new PostgresDbHandler(dataSource);
        WorkersDao workersDao = new PostgresWorkersDao(dataSource);
        DbExporter excelDbExporter = new ExcelDbExporter(dataSource);

        return List.of(
                new ShowTablesCommand(dbHandler),
                new CreateTableCommand(workersDao),
                new CreateWorkerCommand(workersDao),
                new ShowTableRowsCommand(workersDao),
                new ExportTableCommand(excelDbExporter)
        );
    }

    private static String getPropertyOrThrow(String key) {
        return Optional.ofNullable(System.getProperty(key))
                .orElseThrow(() -> new IllegalArgumentException("Please enter required property:" + key));
    }

    private static DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(getPropertyOrThrow("dbUrl"));
        dataSource.setUser(getPropertyOrThrow("dbUser"));
        dataSource.setPassword(getPropertyOrThrow("dbPassword"));

        return dataSource;
    }
}
