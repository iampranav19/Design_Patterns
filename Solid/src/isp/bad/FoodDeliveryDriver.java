package isp.bad;

// BAD: FoodDeliveryDriver only delivers food, but is forced to implement
// ride methods it has no business having.
public class FoodDeliveryDriver implements UberDriver {

    @Override
    public void acceptRide() {
        throw new UnsupportedOperationException("FoodDeliveryDriver doesn't drive riders");
    }

    @Override
    public void pickUpRider() {
        throw new UnsupportedOperationException("FoodDeliveryDriver doesn't drive riders");
    }

    @Override
    public void dropOffRider() {
        throw new UnsupportedOperationException("FoodDeliveryDriver doesn't drive riders");
    }

    @Override
    public void rateRider() {
        throw new UnsupportedOperationException("FoodDeliveryDriver doesn't drive riders");
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