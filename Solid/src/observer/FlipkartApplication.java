package observer;

public class FlipkartApplication {

    public static void main(String[] args) {
        new NotificationService();
        new InvoiceManagementService();

        Flipkart flipkart = new Flipkart();
        Order order = new Order(122, 5000);

        flipkart.placeOrder(order);
        flipkart.cancelOrder(order);
    }
}
