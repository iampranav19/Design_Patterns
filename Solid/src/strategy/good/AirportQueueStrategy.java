package strategy.good;

public class AirportQueueStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " from airport queue.");
    }
}