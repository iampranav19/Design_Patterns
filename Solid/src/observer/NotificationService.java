package observer;

public class NotificationService implements Subscriber<Order>{

    NotificationService()
    {
        Flipkart.registerSubscriber(Events.NOTIFIED, this);
    }

    @Override
    public void listen(Events event, Order order) {
        sendNotification();
    }

    public void sendNotification()
    {
        System.out.println("Sending notification to the user");
    }
}
