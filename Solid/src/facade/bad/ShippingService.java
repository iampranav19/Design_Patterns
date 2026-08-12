package facade.bad;

public class ShippingService {

    public void scheduleDelivery(String item, String address) {
        System.out.println("Scheduling delivery of " + item + " to " + address);
    }
}