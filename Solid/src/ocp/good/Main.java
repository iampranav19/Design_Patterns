package ocp.good;

public class Main {

    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();
        double weightKg = 5.0;

        System.out.println("Standard:      $" + calculator.calculate(new StandardShipping(), weightKg));
        System.out.println("Express:       $" + calculator.calculate(new ExpressShipping(), weightKg));
        System.out.println("International: $" + calculator.calculate(new InternationalShipping(), weightKg));
        System.out.println("Drone:         $" + calculator.calculate(new DroneShipping(), weightKg));
        // Adding "PickUp" shipping? Just create PickUpShipping.java — nothing else changes.
    }
}