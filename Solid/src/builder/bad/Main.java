package builder.bad;

public class Main {

    public static void main(String[] args) {
        // What do these booleans even mean without checking the constructor signature?
        Pizza pizza = new Pizza("Large", true, true, false, true, false);
        System.out.println(pizza);

        // Want a pizza with just mushrooms and a thin crust? You still have to pass
        // every single parameter, in the exact right order.
        Pizza mushroomPizza = new Pizza("Medium", true, false, true, false, true);
        System.out.println(mushroomPizza);
    }
}