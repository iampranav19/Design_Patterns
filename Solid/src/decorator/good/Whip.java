package decorator.good;

public class Whip extends CondimentDecorator {

    public Whip(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Whip";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.75;
    }
}