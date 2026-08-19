package decorator.good;

public class Main {

    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee = new Milk(coffee);
        coffee = new Sugar(coffee);
        coffee = new Whip(coffee);

        System.out.println(coffee.describe() + " -> $" + coffee.cost());
        // Same combo as decorator.bad.Main, built by wrapping instead of a
        // dedicated CoffeeWithMilkAndSugarAndWhip subclass — and unlike that
        // subclass, this composition isn't locked into one fixed chain: e.g.
        // new Whip(new Milk(coffee)) (skipping Sugar) needs no new class,
        // and doubling up (new Milk(new Milk(coffee))) just works too.
    }
}