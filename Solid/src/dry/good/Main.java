package dry.good;

public class Main {

    public static void main(String[] args) {
        double subtotal = 100.0;

        Order order = new Order(subtotal);
        Invoice invoice = new Invoice(subtotal);

        System.out.println("Order total:   $" + order.calculateTotal());
        System.out.println("Invoice total: $" + invoice.calculateTotal());
        // Same subtotal, same tax logic, guaranteed the same total — there's
        // only one place the rate could possibly be wrong.
    }
}