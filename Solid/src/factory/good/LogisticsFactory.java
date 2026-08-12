package factory.good;

// GOOD: All object-creation logic lives in ONE place. Callers never see
// Road/Sea/Air directly — they just ask the factory for a Logistics.
public class LogisticsFactory {

    public static Logistics create(String transportType) {
        switch (transportType) {
            case "ROAD":
                return new Road();
            case "SEA":
                return new Sea();
            case "AIR":
                return new Air();
            default:
                throw new IllegalArgumentException("Unknown transport type: " + transportType);
        }
    }
}