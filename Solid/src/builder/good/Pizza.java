package builder.good;

// GOOD: Pizza is immutable and built through a fluent Builder. The call site
// reads like a sentence, optional ingredients are truly optional, and the
// order the builder methods are called in doesn't matter.
public class Pizza {

    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    private final boolean extraCheese;
    private final boolean thinCrust;

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
        this.extraCheese = builder.extraCheese;
        this.thinCrust = builder.thinCrust;
    }

    @Override
    public String toString() {
        return size + " pizza [cheese=" + cheese + ", pepperoni=" + pepperoni
                + ", mushrooms=" + mushrooms + ", extraCheese=" + extraCheese
                + ", thinCrust=" + thinCrust + "]";
    }

    public static class Builder {
        private final String size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean mushrooms;
        private boolean extraCheese;
        private boolean thinCrust;

        public Builder(String size) {
            this.size = size;
        }

        public Builder cheese() {
            this.cheese = true;
            return this;
        }

        public Builder pepperoni() {
            this.pepperoni = true;
            return this;
        }

        public Builder mushrooms() {
            this.mushrooms = true;
            return this;
        }

        public Builder extraCheese() {
            this.extraCheese = true;
            return this;
        }

        public Builder thinCrust() {
            this.thinCrust = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}