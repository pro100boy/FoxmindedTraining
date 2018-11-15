package task_2;

import dbutils.ConnectionFactory;
import dbutils.PopulateDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionScript {

    public static void main(String[] args) throws IOException, SQLException {
        final Connection connection = ConnectionFactory.getConnection();
        PopulateDB.recreateAndPopulateDB("init_task2.sql", connection);
        connection.setSchema("task2");
        RecognitionService recognitionService = new RecognitionService(new Gateway(connection));
        recognitionService.calculateRevenueRecognitions(3);
    }
}
