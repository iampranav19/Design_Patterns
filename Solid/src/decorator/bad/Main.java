package decorator.bad;

// BAD: every add-on combination needs its own subclass. With 4 add-ons
// (Milk, Sugar, Whip, Caramel) that's up to 15 non-empty combinations — a
// 5th add-on roughly doubles that again. Only 3 combos are written here;
// the rest would each need their own class the same way.
//
// Worse: since each subclass extends exactly one parent, "Milk + Whip"
// (skipping Sugar) can't reuse CoffeeWithMilkAndSugarAndWhip at all — it
// needs yet another standalone subclass, because inheritance only gives you
// linear chains, not free combination.
public class Main {

    public static void main(String[] args) {
        Coffee coffee = new CoffeeWithMilkAndSugarAndWhip();

        System.out.println(coffee.describe() + " -> $" + coffee.cost());
    }
}