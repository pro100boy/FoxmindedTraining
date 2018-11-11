package task_2;

public class TransactionScript {

    public static void main(String[] args) {
        RecognitionService recognitionService = new RecognitionService(new Gateway());
        recognitionService.calculateRevenueRecognitions(3);
    }
}
