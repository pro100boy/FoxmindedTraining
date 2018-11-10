package task_2.db;

public class QueryHolder {
    public static final String insertRecognitionQuery =
            "INSERT INTO revenue_recognitions (contract, amount, recognized_on, days) VALUES (?,?,?,?)";

    public static final String findContractQuery = "SELECT * FROM contracts WHERE id = ?";

    public static final String findProductQuery = "SELECT * FROM products WHERE id = ?";
}
