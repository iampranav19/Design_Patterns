package factory.bad;

// BAD: The client is welded to every concrete Logistics type. This creation
// if/else is duplicated wherever a Logistics is needed, and adding a new
// transport type means hunting down and editing every one of those spots.
public class Main {

    public static void main(String[] args) {
        String transportType = "ROAD";

        Logistics logistics;
        if (transportType.equals("ROAD")) {
            logistics = new Road();
        } else if (transportType.equals("SEA")) {
            logistics = new Sea();
        } else if (transportType.equals("AIR")) {
            logistics = new Air();
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + transportType);
        }

        logistics.send();
        // Adding "RAIL"? This if/else has to be found and edited here — and
        // everywhere else in the codebase that also creates a Logistics.
    }
}