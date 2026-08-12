package lsp.good;

// GOOD: Crypto payments only implement what they can honestly support.
// No forced refund() method, no broken promises, no LSP violation.
public class CryptoPayment implements Payment {

    private final double amount;

    public CryptoPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing crypto payment of $" + amount);
    }
}