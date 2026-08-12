package adapter.good;

public class Main {

    public static void main(String[] args) {
        PaymentProcessor processor = new StripePaymentAdapter(new StripeGateway());
        checkout(processor, 49.99);
    }

    private static void checkout(PaymentProcessor processor, double amount) {
        processor.pay(amount);
        // checkout() only knows about PaymentProcessor — it has no idea Stripe,
        // cents, or any third-party detail exists behind the adapter.
    }
}