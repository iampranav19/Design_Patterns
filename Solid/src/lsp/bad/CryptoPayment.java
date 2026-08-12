package lsp.bad;

// BAD: Crypto payments can't be refunded, but this class is FORCED to override
// refund() because it extends Payment. Substituting CryptoPayment wherever a
// Payment is expected breaks client code — that's the LSP violation.
public class CryptoPayment extends Payment {

    public CryptoPayment(double amount) {
        super(amount);
    }

    @Override
    public void refund() {
        throw new UnsupportedOperationException("Crypto payments cannot be refunded");
    }
}