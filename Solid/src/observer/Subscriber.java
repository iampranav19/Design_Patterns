package observer;

public interface Subscriber<T> {

    void listen(Events event, T details);

}
