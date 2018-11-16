package task_2;

import dbutils.PopulateDB;

import java.sql.Connection;
import java.sql.SQLException;

import static dbutils.ConnectionFactory.getConnection;

public class TransactionScript {

    public static void main(String[] args) throws SQLException {
        final Connection connection = getConnection();
        PopulateDB.recreateAndPopulateDB("init_task2.sql");

        connection.setSchema("task2");
        RecognitionService recognitionService = new RecognitionService(new Gateway(connection));
        recognitionService.calculateRevenueRecognitions(3);
    }
}
