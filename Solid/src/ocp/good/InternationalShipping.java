package ocp.good;

public class InternationalShipping implements ShippingStrategy {

    @Override
    public double calculate(double weightKg) {
        return weightKg * 7.0 + 50.0;
    }
}