package isp.bad;

// BAD: RideDriver only drives riders, but is forced to implement food-delivery
// methods it has no business having.
public class RideDriver implements UberDriver {

    @Override
    public void acceptRide() {
        System.out.println("Ride accepted");
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
        throw new UnsupportedOperationException("RideDriver doesn't deliver food");
    }

    @Override
    public void pickUpFood() {
        throw new UnsupportedOperationException("RideDriver doesn't deliver food");
    }

    @Override
    public void deliverFood() {
        throw new UnsupportedOperationException("RideDriver doesn't deliver food");
    }
}