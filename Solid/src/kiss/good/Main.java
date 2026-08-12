package kiss.good;

public class Main {

    public static void main(String[] args) {
        double orderTotal = 62.0;
        System.out.println("Order total $" + orderTotal + " eligible for free shipping? "
                + ShippingCalculator.isEligibleForFreeShipping(orderTotal));
    }
}