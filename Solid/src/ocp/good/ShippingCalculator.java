package ocp.good;

// GOOD: This class never changes when a new shipping type is introduced.
// It is closed for modification, open for extension via ShippingStrategy.
public class ShippingCalculator {

    public double calculate(ShippingStrategy strategy, double weightKg) {
        return strategy.calculate(weightKg);
    }
}