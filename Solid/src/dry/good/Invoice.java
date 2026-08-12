package dry.good;

public class Invoice {

    private final double subtotal;

    public Invoice(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateTotal() {
        return TaxCalculator.calculateTotal(subtotal);
    }
}