package ocp.bad;

// BAD: Every time a new shipping type is added (Drone, SameDay, PickUp...),
// this class must be opened and modified.
// This violates OCP — it is NOT closed for modification.
public class ShippingCalculator {

    public double calculate(Order order) {
        if (order.getShippingType().equals("STANDARD")) {
            return order.getOrderWeight() * 1.5;

        } else if (order.getShippingType().equals("EXPRESS")) {
            return order.getOrderWeight() * 3.0 + 10.0;

        } else if (order.getShippingType().equals("INTERNATIONAL")) {
            return order.getOrderWeight() * 7.0 + 50.0;
        }

        // Adding "DRONE" delivery? You must come back and modify THIS class.
        throw new IllegalArgumentException("Unknown shipping type: " + order.getShippingType());
    }
}