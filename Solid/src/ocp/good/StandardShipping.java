package ocp.good;

public class StandardShipping implements ShippingStrategy {

    @Override
    public double calculate(double weightKg) {
        return weightKg * 1.5;
    }
}