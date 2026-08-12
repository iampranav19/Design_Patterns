package dry.bad;

// BAD: Tax calculation logic is duplicated here AND in Invoice.java.
public class Order {

    private static final double TAX_RATE = 0.08;

    private final double subtotal;

    public Order(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateTotal() {
        double tax = subtotal * TAX_RATE;
        return subtotal + tax;
    }
}