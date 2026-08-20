package strategy.bad;

public class RideMatchingService {

    public void matchRider(String riderLocation, String matchingType) {
        if (matchingType.equals("NEAREST")) {
            System.out.println("Matching rider at " + riderLocation + " with nearest driver.");
        } else if (matchingType.equals("SURGE_PRIORITY")) {
            System.out.println("Matching rider at " + riderLocation + " based on surge pricing priority.");
        } else if (matchingType.equals("AIRPORT_QUEUE")) {
            System.out.println("Matching rider at " + riderLocation + " from airport queue.");
        } else {
            System.out.println("Invalid matching strategy provided.");
        }
    }
}