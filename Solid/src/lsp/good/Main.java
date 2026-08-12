package lsp.good;

public class Main {

    public static void main(String[] args) {
        Payment[] payments = {
                new CardPayment(100.0),
                new CryptoPayment(50.0)
        };

        for (Payment payment : payments) {
            payment.processPayment();

            if (payment instanceof Refundable refundable) {
                refundable.refund();
            } else {
                System.out.println("Refund not supported for this payment type");
            }
        }
        // Every Payment can be safely substituted — none of them break processPayment().
        // Refundable is opt-in, so no subtype is forced to fake a capability it doesn't have.
    }
}