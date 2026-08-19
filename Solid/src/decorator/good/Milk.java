package decorator.good;

public class Milk extends CondimentDecorator {

    public Milk(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Milk";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.50;
    }
}