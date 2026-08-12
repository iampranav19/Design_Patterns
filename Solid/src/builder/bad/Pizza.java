package builder.bad;

// BAD: A "telescoping constructor" — too many parameters, all positional.
// The call site is unreadable and it's easy to swap two booleans by mistake.
public class Pizza {

    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    private final boolean extraCheese;
    private final boolean thinCrust;

    public Pizza(String size, boolean cheese, boolean pepperoni, boolean mushrooms,
                 boolean extraCheese, boolean thinCrust) {
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.mushrooms = mushrooms;
        this.extraCheese = extraCheese;
        this.thinCrust = thinCrust;
    }

    @Override
    public String toString() {
        return size + " pizza [cheese=" + cheese + ", pepperoni=" + pepperoni
                + ", mushrooms=" + mushrooms + ", extraCheese=" + extraCheese
                + ", thinCrust=" + thinCrust + "]";
    }
}