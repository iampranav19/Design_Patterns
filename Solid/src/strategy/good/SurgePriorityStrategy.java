package strategy.good;

public class SurgePriorityStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " based on surge pricing priority.");
    }
}