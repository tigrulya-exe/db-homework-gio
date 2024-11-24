package university.homework.db;

import university.homework.state.Worker;

import java.sql.SQLException;
import java.util.List;

public interface WorkersDao {

    void createNewTable(String tableName) throws SQLException;

    void saveWorker(String tableName, Worker worker) throws SQLException;

    List<Worker> getWorkers(String tableName) throws SQLException;
}
