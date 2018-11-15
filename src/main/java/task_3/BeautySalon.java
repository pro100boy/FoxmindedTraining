package task_3;

import dbutils.ConnectionFactory;
import dbutils.PopulateDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BeautySalon {
    public static void main(String[] args) throws IOException, SQLException {
        final Connection connection = ConnectionFactory.getConnection();
        PopulateDB.recreateAndPopulateDB("init_task3.sql", connection);
        connection.setSchema("task3");
    }
}
