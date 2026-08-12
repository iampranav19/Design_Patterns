package adapter.bad;

// BAD: StripeGateway's interface doesn't match what our checkout code expects
// (PaymentProcessor.pay(double)). Every call site has to know Stripe-specific
// details — converting dollars to cents itself — and StripeGateway can't be
// passed anywhere a PaymentProcessor is expected, since it doesn't implement it.
public class Main {

    public static void main(String[] args) {
        StripeGateway stripe = new StripeGateway();

        double amount = 49.99;
        // Every caller has to remember: Stripe wants cents, as an int.
        stripe.makeCharge((int) Math.round(amount * 100));

        // checkout(PaymentProcessor processor) has no way to accept `stripe` directly —
        // it simply doesn't implement PaymentProcessor.
    }
}