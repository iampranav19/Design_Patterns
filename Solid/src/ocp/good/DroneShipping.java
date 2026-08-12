package ocp.good;

// GOOD: New shipping type added — zero changes to ShippingCalculator or any existing class.
public class DroneShipping implements ShippingStrategy {

    @Override
    public double calculate(double weightKg) {
        return weightKg * 2.0 + 25.0;
    }
}