package observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flipkart {

    private static Map<Events, List<Subscriber>> subscribers = new HashMap<>();

    public static void registerSubscriber(Events events, Subscriber subscriber) {
        subscribers.computeIfAbsent(events, e -> new ArrayList<>()).add(subscriber);
    }
    public void placeOrder(Order order) {
        notify(Events.ORDER_PLACED, order);
        notify(Events.NOTIFIED, order);
    }
    public void cancelOrder(Order order) {
        notify(Events.CANCELLED, order);
    }

    public void notify(Events event, Order order) {
        for (Subscriber subscriber : subscribers.getOrDefault(event, Collections.emptyList())) {
            subscriber.listen(event, order);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.values().forEach(list -> list.remove(subscriber));
    }
}
