package lsp.bad;

public class Main {

    public static void main(String[] args) {
        Payment[] payments = {
                new Payment(100.0),
                new CryptoPayment(50.0)
        };

        for (Payment payment : payments) {
            payment.processPayment();
            payment.refund(); // Crashes on CryptoPayment — callers can't trust the Payment contract
        }
    }
}