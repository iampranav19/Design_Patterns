package isp.good;

// Segregated contract: only food-delivery behavior.
public interface FoodDeliveryDriver {
    void acceptFoodDelivery();
    void pickUpFood();
    void deliverFood();
}