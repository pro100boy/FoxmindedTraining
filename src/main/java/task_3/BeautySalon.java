package task_3;

import dbutils.PopulateDB;
import task_3.dao.ServiceGateway;
import task_3.dao.TExecutor;

import java.sql.Connection;
import java.sql.SQLException;

import static dbutils.ConnectionFactory.getConnection;

public class BeautySalon {
    public static void main(String[] args) throws SQLException {
        PopulateDB.recreateAndPopulateDB("init_task3.sql");

        Connection connection = getConnection();
        connection.setSchema("task3");
        TExecutor tExecutor = new TExecutor();
        ServiceGateway gateway = new ServiceGateway(tExecutor, connection);
        System.out.println(gateway.findOne(1));
        System.out.println(gateway.findAll());
    }
}
