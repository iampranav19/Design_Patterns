package lsp.good;

public class CardPayment implements Payment, Refundable {

    private final double amount;

    public CardPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing card payment of $" + amount);
    }

    @Override
    public void refund() {
        System.out.println("Refunding $" + amount + " to card");
    }
}