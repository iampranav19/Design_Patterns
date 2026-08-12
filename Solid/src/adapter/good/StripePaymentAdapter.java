package adapter.good;

// GOOD: Adapts StripeGateway's incompatible interface to the PaymentProcessor
// contract our checkout code expects. The dollars-to-cents conversion lives in
// exactly one place instead of being duplicated at every call site.
public class StripePaymentAdapter implements PaymentProcessor {

    private final StripeGateway stripeGateway;

    public StripePaymentAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void pay(double amountInDollars) {
        int amountInCents = (int) Math.round(amountInDollars * 100);
        stripeGateway.makeCharge(amountInCents);
    }
}