package isp.good;

public class Main {

    public static void main(String[] args) {
        RideDriver uberX = new UberXDriver();
        uberX.acceptRide();
        uberX.pickUpRider();
        uberX.dropOffRider();
        uberX.rateRider();

        FoodDeliveryDriver uberEats = new UberEatsDriver();
        uberEats.acceptFoodDelivery();
        uberEats.pickUpFood();
        uberEats.deliverFood();

        UberProDriver uberPro = new UberProDriver();
        uberPro.acceptRide();
        uberPro.acceptFoodDelivery();
        // Every driver only implements the interfaces it actually supports —
        // no UnsupportedOperationException lurking anywhere.
    }
}