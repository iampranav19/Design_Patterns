package isp.good;

// Segregated contract: only ride-related behavior.
public interface RideDriver {
    void acceptRide();
    void pickUpRider();
    void dropOffRider();
    void rateRider();
}