package isp.good;

// GOOD: Only implements the interface it actually supports.
public class UberXDriver implements RideDriver {

    @Override
    public void acceptRide() {
        System.out.println("UberX ride accepted");
    }

    @Override
    public void pickUpRider() {
        System.out.println("Picking up rider");
    }

    @Override
    public void dropOffRider() {
        System.out.println("Dropping off rider");
    }

    @Override
    public void rateRider() {
        System.out.println("Rating rider 5 stars");
    }
}