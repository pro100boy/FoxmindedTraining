package task_2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class RecognitionService {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    private final Gateway gateway;

    public RecognitionService(Gateway gateway) {
        this.gateway = gateway;
    }

    public void calculateRevenueRecognitions(int contractId) {

        try {
            logger.info("calculate revenue recognitions");

            // get contract by Id
            ResultSet rsContract = gateway.findContract(contractId);
            rsContract.next();

            Long revenue = rsContract.getLong("revenue");
            LocalDate date = rsContract.getObject("date_signed", LocalDate.class);
            int productId = rsContract.getInt("product");

            // get product by Id
            ResultSet rsProduct = gateway.findProduct(productId);
            rsProduct.next();
            String productType = rsProduct.getString("type").toUpperCase();

            Long oneThirdPart = Math.floorDiv(revenue, 3);

            switch (productType) {
                case "W":
                    gateway.insertRecognition(contractId, revenue, date, 0);
                    break;
                case "S":
                    gateway.insertRecognition(contractId, oneThirdPart, date, 0);
                    gateway.insertRecognition(contractId, oneThirdPart, date.plusDays(60), 60);
                    gateway.insertRecognition(contractId, oneThirdPart, date.plusDays(90), 90);
                    break;
                case "D":
                    gateway.insertRecognition(contractId, oneThirdPart, date, 0);
                    gateway.insertRecognition(contractId, oneThirdPart, date.plusDays(30), 30);
                    gateway.insertRecognition(contractId, oneThirdPart, date.plusDays(60), 60);
                    break;
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
