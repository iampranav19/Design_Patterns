package ocp.good;

// The contract: every shipping type knows how to calculate its own cost.
public interface ShippingStrategy {
    double calculate(double weightKg);
}