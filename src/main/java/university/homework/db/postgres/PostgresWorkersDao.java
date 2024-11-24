package university.homework.db.postgres;

import university.homework.db.WorkersDao;
import university.homework.state.Worker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresWorkersDao implements WorkersDao {
    private static final String INSERT_WORKER_SQL_TEMPLATE =
            "INSERT INTO %S (name, age, salary) VALUES (?, ?, ?)";
    private static final String GET_WORKERS_SQL_TEMPLATE =
            "SELECT name, age, salary FROM %S";
    private static final String CREATE_NEW_TABLE_SQL_TEMPLATE = """
            CREATE TABLE %S (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255),
                age INTEGER,
                salary INTEGER
            )
            """;

    private final DataSource dataSource;

    public PostgresWorkersDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createNewTable(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CREATE_NEW_TABLE_SQL_TEMPLATE.formatted(tableName));
        }
    }

    @Override
    public void saveWorker(String tableName, Worker worker) throws SQLException {
        String preparedStatementSql = INSERT_WORKER_SQL_TEMPLATE.formatted(tableName);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementSql)
        ) {
            preparedStatement.setString(1, worker.getName());
            preparedStatement.setInt(2, worker.getAge());
            preparedStatement.setInt(3, worker.getSalary());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Worker> getWorkers(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(GET_WORKERS_SQL_TEMPLATE.formatted(tableName));

            List<Worker> workers = new ArrayList<>();
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setName(resultSet.getString("name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setSalary(resultSet.getInt("salary"));
                workers.add(worker);
            }
            return workers;
        }
    }
}
