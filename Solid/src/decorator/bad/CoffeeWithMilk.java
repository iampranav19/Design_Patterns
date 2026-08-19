package decorator.bad;

public class CoffeeWithMilk extends Coffee {

    @Override
    public double cost() {
        return super.cost() + 0.50;
    }

    @Override
    public String describe() {
        return super.describe() + " + Milk";
    }
}