package university.homework.db;

import java.sql.SQLException;
import java.util.List;

public interface DbHandler {
    List<String> getDbTableNames() throws SQLException;
}
