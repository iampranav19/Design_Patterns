package kiss.good;

// GOOD: The whole rule, in one line. No interface, no factory, no evaluator —
// just the question, answered directly.
public class ShippingCalculator {

    private static final double FREE_SHIPPING_THRESHOLD = 50.0;

    public static boolean isEligibleForFreeShipping(double orderTotal) {
        return orderTotal >= FREE_SHIPPING_THRESHOLD;
    }
}