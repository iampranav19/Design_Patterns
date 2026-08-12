package dry.good;

// GOOD: The ONE place tax logic lives. Change the rate here and every caller
// — Order, Invoice, anything else — stays in sync automatically.
public class TaxCalculator {

    private static final double TAX_RATE = 0.08;

    public static double calculateTotal(double subtotal) {
        double tax = subtotal * TAX_RATE;
        return subtotal + tax;
    }
}