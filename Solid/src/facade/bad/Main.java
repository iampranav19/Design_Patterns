package facade.bad;

// BAD: The client has to know about — and correctly orchestrate — 4 separate
// subsystems, in the exact right order, everywhere an order is placed (web
// checkout, mobile app, admin panel...). Miss a step or reorder it and
// you've shipped a bug.
public class Main {

    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        PaymentService payment = new PaymentService();
        ShippingService shipping = new ShippingService();
        NotificationService notification = new NotificationService();

        String customer = "Alice";
        String item = "Wireless Mouse";
        int quantity = 1;
        double amount = 25.99;
        String address = "123 Main St";

        if (inventory.checkStock(item, quantity)) {
            inventory.reserveStock(item, quantity);
            if (payment.charge(customer, amount)) {
                shipping.scheduleDelivery(item, address);
                notification.sendConfirmation(customer, item);
            }
        }
        // Every checkout entry point in the codebase has to repeat this exact
        // sequence — and get it exactly right.
    }
}