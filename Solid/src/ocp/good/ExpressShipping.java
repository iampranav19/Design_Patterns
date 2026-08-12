package ocp.good;

public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculate(double weightKg) {
        return weightKg * 3.0 + 10.0;
    }
}