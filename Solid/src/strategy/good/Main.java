package strategy.good;

public class Main {

    public static void main(String[] args) {
        RideMatchingService nearestService = new RideMatchingService(new NearestDriverStrategy());
        nearestService.matchRider("Downtown");

        RideMatchingService surgeService = new RideMatchingService(new SurgePriorityStrategy());
        surgeService.matchRider("Downtown");

        RideMatchingService airportService = new RideMatchingService(new AirportQueueStrategy());
        airportService.matchRider("Airport Terminal 2");

        // No equivalent of strategy.bad's "NEARST" typo is possible here —
        // passing anything that isn't a MatchingStrategy fails to compile.
    }
}