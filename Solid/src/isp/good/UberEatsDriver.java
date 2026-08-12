package isp.good;

// GOOD: Only implements the interface it actually supports.
public class UberEatsDriver implements FoodDeliveryDriver {

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