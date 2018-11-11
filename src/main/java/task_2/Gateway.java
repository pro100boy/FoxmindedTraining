package task_2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

import static task_2.db.ConnectionFactory.getConnection;
import static task_2.db.QueryHolder.*;

public class Gateway {
    private static final Class<Gateway> clazz = Gateway.class;
    private static final Logger logger = Logger.getLogger(clazz.getSimpleName());

    private final Connection db;

    public Gateway() {
        logger.info("try to get connection");
        try {
            db = getConnection();
        } catch (SQLException | IOException e) {
            logger.severe("Unable to get connection");
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResultSet findContract(int contractId) throws SQLException {
        logger.info("find contract " + contractId);
        PreparedStatement stmt = db.prepareStatement(findContractQuery);
        stmt.setInt(1, contractId);
        return stmt.executeQuery();
    }

    public ResultSet findProduct(int productId) throws SQLException {
        logger.info("find product " + productId);
        PreparedStatement stmt = db.prepareStatement(findProductQuery);
        stmt.setInt(1, productId);

        return stmt.executeQuery();
    }

    public void insertRecognition(int contract, long amount, LocalDate recognizedOn, int days) throws SQLException {
        logger.info(String.format(
                "insert recognition with params: contractId = %s, amount = %s, recognizedOn = %s, days = %s",
                contract, amount, recognizedOn, days));
        PreparedStatement stmt = db.prepareStatement(insertRecognitionQuery);

        stmt.setInt(1, contract);
        stmt.setLong(2, amount);
        stmt.setObject(3, recognizedOn);
        stmt.setInt(4, days);
        stmt.executeUpdate();

    }
}
