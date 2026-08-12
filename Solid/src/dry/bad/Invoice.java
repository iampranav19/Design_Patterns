package dry.bad;

// BAD: Same tax calculation as Order.java, copy-pasted — and now it's DRIFTED.
// Whoever bumped the tax rate to 8% in Order.java forgot this copy still exists.
public class Invoice {

    private static final double TAX_RATE = 0.05; // forgot to update when Order.java changed!

    private final double subtotal;

    public Invoice(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateTotal() {
        double tax = subtotal * TAX_RATE;
        return subtotal + tax;
    }
}