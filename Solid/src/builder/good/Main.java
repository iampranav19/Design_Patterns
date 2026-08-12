package builder.good;

public class Main {

    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder("Large")
                .cheese()
                .pepperoni()
                .extraCheese()
                .build();
        System.out.println(pizza);

        // Only want mushrooms and a thin crust? Just skip the calls you don't need —
        // no need to pass six positional arguments in the exact right order.
        Pizza mushroomPizza = new Pizza.Builder("Medium")
                .cheese()
                .mushrooms()
                .thinCrust()
                .build();
        System.out.println(mushroomPizza);
    }
}