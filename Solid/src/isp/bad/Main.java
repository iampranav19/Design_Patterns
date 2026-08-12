package isp.bad;

public class Main {

    public static void main(String[] args) {
        UberDriver[] drivers = {
                new RideDriver(),
                new FoodDeliveryDriver()
        };

        for (UberDriver driver : drivers) {
            driver.acceptRide();
            driver.pickUpRider();
            driver.dropOffRider();
            driver.rateRider();

            driver.acceptFoodDelivery(); // Crashes for RideDriver — forced to expose a method it can't support
            driver.pickUpFood();
            driver.deliverFood();
        }
    }
}