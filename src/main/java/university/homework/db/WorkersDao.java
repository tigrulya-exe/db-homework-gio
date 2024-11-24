package university.homework.db;

import java.sql.SQLException;
import java.util.List;
import university.homework.state.Worker;

public interface WorkersDao {

    void createNewTable(String tableName) throws SQLException;

    void saveWorkers(List<Worker> workers) throws SQLException;

    List<Worker> getWorkers(String tableName) throws SQLException;
}
