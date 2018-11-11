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

            long totalRevenue = rsContract.getLong("revenue");
            LocalDate date = rsContract.getObject("date_signed", LocalDate.class);
            int productId = rsContract.getInt("product");

            // get product by Id
            ResultSet rsProduct = gateway.findProduct(productId);
            rsProduct.next();
            String productType = rsProduct.getString("type").toUpperCase();

            long[] allocations = allocate(totalRevenue);

            switch (productType) {
                case "W":
                    gateway.insertRecognition(contractId, totalRevenue, date, 0);
                    break;
                case "S":
                    gateway.insertRecognition(contractId, allocations[0], date, 0);
                    gateway.insertRecognition(contractId, allocations[1], date.plusDays(60), 60);
                    gateway.insertRecognition(contractId, allocations[2], date.plusDays(90), 90);
                    break;
                case "D":
                    gateway.insertRecognition(contractId, allocations[0], date, 0);
                    gateway.insertRecognition(contractId, allocations[1], date.plusDays(30), 30);
                    gateway.insertRecognition(contractId, allocations[2], date.plusDays(60), 60);
                    break;
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private long[] allocate(long revenue) {
        long oneThirdPart = Math.floorDiv(revenue, 3);
        long rest = revenue - oneThirdPart * 2;
        return new long[]{oneThirdPart, oneThirdPart, rest};
    }
}
