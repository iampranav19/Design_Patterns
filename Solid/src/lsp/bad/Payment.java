package lsp.bad;

public class Payment {

    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    public void processPayment() {
        System.out.println("Processing payment of $" + amount);
    }

    public void refund() {
        System.out.println("Refunding $" + amount);
    }
}