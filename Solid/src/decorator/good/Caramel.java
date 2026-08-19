package decorator.good;

public class Caramel extends CondimentDecorator {

    public Caramel(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String describe() {
        return wrapped.describe() + " + Caramel";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.60;
    }
}