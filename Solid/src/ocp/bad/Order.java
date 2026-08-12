package ocp.bad;

public class Order {
    private String shippingType; // "STANDARD", "EXPRESS", "INTERNATIONAL"
    private double orderWeight;

    public Order(String shippingType, double orderWeight) {
        this.shippingType = shippingType;
        this.orderWeight = orderWeight;
    }

    public String getShippingType() { return shippingType; }
    public double getOrderWeight() { return orderWeight; }
}