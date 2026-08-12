package ocp.bad;

public class Main {

    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();

        Order standard    = new Order("STANDARD", 5.0);
        Order express     = new Order("EXPRESS", 5.0);
        Order intl        = new Order("INTERNATIONAL", 5.0);

        System.out.println("Standard:      $" + calculator.calculate(standard));
        System.out.println("Express:       $" + calculator.calculate(express));
        System.out.println("International: $" + calculator.calculate(intl));

        // To add Drone shipping, you must open ShippingCalculator.java and add another else-if
    }
}