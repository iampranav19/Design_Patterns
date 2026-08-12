package facade.good;

public class Main {

    public static void main(String[] args) {
        CheckoutFacade checkout = new CheckoutFacade();

        boolean success = checkout.placeOrder("Alice", "Wireless Mouse", 1, 25.99, "123 Main St");
        System.out.println("Order placed: " + success);
        // Web checkout, mobile app, admin panel — every entry point calls this
        // ONE method. The correct orchestration lives in exactly one place.
    }
}