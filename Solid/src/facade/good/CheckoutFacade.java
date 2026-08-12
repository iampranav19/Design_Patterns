package facade.good;

// GOOD: One simple entry point that hides the 4-subsystem orchestration.
// Callers don't need to know InventoryService, PaymentService, ShippingService,
// or NotificationService even exist — or in what order to call them.
public class CheckoutFacade {

    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();
    private final NotificationService notification = new NotificationService();

    public boolean placeOrder(String customer, String item, int quantity, double amount, String address) {
        if (!inventory.checkStock(item, quantity)) {
            return false;
        }
        inventory.reserveStock(item, quantity);

        if (!payment.charge(customer, amount)) {
            return false;
        }

        shipping.scheduleDelivery(item, address);
        notification.sendConfirmation(customer, item);
        return true;
    }
}