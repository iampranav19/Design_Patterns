package dry.good;

public class Order {

    private final double subtotal;

    public Order(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateTotal() {
        return TaxCalculator.calculateTotal(subtotal);
    }
}