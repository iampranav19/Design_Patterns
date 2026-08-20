package strategy.bad;

// BAD: a new matching algorithm (e.g. "POOL_MATCH" for shared rides) means
// opening this already-tested if/else again. And since the type is just a
// String, there's no compile-time check that it's spelled right — a typo
// silently falls through to "Invalid matching strategy provided," instead
// of failing to compile.
public class Main {

    public static void main(String[] args) {
        RideMatchingService service = new RideMatchingService();

        service.matchRider("Downtown", "NEAREST");
        service.matchRider("Downtown", "SURGE_PRIORITY");
        service.matchRider("Airport Terminal 2", "AIRPORT_QUEUE");

        // A typo the compiler can't catch — "NEAREST" misspelled.
        service.matchRider("Downtown", "NEARST");
    }
}