package strategy.good;

public class NearestDriverStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " with nearest driver.");
    }
}