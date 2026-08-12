package lsp.good;

// The contract every payment type honors: it can be processed.
public interface Payment {
    void processPayment();
}