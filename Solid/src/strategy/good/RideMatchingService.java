package strategy.good;

// GOOD: takes a MatchingStrategy instead of a String type code. There's no
// if/else here to reopen when a new algorithm ships, and an unrecognized
// strategy can't happen — the compiler enforces that whatever's passed in
// implements MatchingStrategy.
public class RideMatchingService {

    private final MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void matchRider(String riderLocation) {
        strategy.match(riderLocation);
    }
}