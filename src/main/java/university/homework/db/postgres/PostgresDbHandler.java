package university.homework.db.postgres;

import university.homework.db.DbHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresDbHandler implements DbHandler {
    private final DataSource dataSource;

    public PostgresDbHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getDbTableNames() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});

            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }
            return tables;
        }
    }
}
