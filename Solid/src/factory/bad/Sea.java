package factory.bad;

public class Sea implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending by sea logic");
    }
}