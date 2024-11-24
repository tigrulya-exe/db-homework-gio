package university.homework.db;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelDbExporter implements DbExporter {
    private final DataSource dataSource;

    public ExcelDbExporter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void exportTable(String tableName) throws SQLException {
        // Using try-with-resources to ensure resources are closed
        try (
                // Establishing the database connection
                Connection connection = dataSource.getConnection();
                // Creating a statement object to execute the query
                Statement statement = connection.createStatement();
                // Executing the query to fetch data from PostgreSQL
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
                // Workbook for Excel creation
                Workbook workbook = new XSSFWorkbook();
                // File output stream to write the Excel file
                FileOutputStream outputStream = new FileOutputStream(tableName + ".xlsx")
        ) {
            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet(tableName);

            // Create the header row
            Row headerRow = sheet.createRow(0);
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(resultSet.getMetaData().getColumnName(i));
            }

            // Write data rows
            int rowCount = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowCount++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }
            }

            // Write the workbook to the output stream
            workbook.write(outputStream);
            System.out.println("Data has been exported to Excel successfully!");
        } catch (Exception exception) {
            throw new SQLException("Error exporting table "
                    + tableName + " to Excel format", exception);
        }
    }
}
