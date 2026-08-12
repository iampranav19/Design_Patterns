package dry.bad;

public class Main {

    public static void main(String[] args) {
        double subtotal = 100.0;

        Order order = new Order(subtotal);
        Invoice invoice = new Invoice(subtotal);

        System.out.println("Order total:   $" + order.calculateTotal());
        System.out.println("Invoice total: $" + invoice.calculateTotal());
        // Same order, same subtotal — but two different totals, because the
        // tax logic was duplicated and one copy quietly drifted out of sync.
    }
}