package adapter.bad;

// Pretend this ships from a third-party SDK jar — we can't change its signature.
public class StripeGateway {

    public void makeCharge(int amountInCents) {
        System.out.println("Charging $" + (amountInCents / 100.0) + " via Stripe");
    }
}