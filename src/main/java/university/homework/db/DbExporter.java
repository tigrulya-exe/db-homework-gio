package university.homework.db;

import java.sql.SQLException;

public interface DbExporter {
    void exportTable(String tableName) throws SQLException;
}
