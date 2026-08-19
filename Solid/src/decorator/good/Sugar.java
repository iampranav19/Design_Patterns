package decorator.good;

public class Sugar extends CondimentDecorator {

    public Sugar(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Sugar";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.25;
    }
}