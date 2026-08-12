package isp.bad;

// BAD: One fat interface forces every driver type to implement methods
// that don't apply to them.
public interface UberDriver {
    void acceptRide();
    void pickUpRider();
    void dropOffRider();
    void rateRider();

    void acceptFoodDelivery();
    void pickUpFood();
    void deliverFood();
}