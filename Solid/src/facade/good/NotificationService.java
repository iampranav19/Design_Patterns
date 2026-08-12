package facade.good;

public class NotificationService {

    public void sendConfirmation(String customer, String item) {
        System.out.println("Sending order confirmation to " + customer + " for " + item);
    }
}