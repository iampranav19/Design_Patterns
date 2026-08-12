package isp.good;

// GOOD: A driver who does both rides and deliveries simply implements both
// interfaces — nobody else is forced to carry methods they don't support.
public class UberProDriver implements RideDriver, FoodDeliveryDriver {

    @Override
    public void acceptRide() {
        System.out.println("UberPro ride accepted");
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

    @Override
    public void acceptFoodDelivery() {
        System.out.println("Food delivery accepted");
    }

    @Override
    public void pickUpFood() {
        System.out.println("Picking up food from restaurant");
    }

    @Override
    public void deliverFood() {
        System.out.println("Delivering food to customer");
    }
}