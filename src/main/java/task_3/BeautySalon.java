package task_3;

import dbutils.ConnectionFactory;
import dbutils.PopulateDB;

import java.io.IOException;
import java.sql.SQLException;

public class BeautySalon {
    public static void main(String[] args) throws IOException, SQLException {
        PopulateDB.recreateAndPopulateDB("init_task3.sql", ConnectionFactory.getConnection("db_task3.properties"));
    }
}
