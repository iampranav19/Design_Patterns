package decorator.bad;

public class CoffeeWithMilkAndSugar extends CoffeeWithMilk {

    @Override
    public double cost() {
        return super.cost() + 0.25;
    }

    @Override
    public String describe() {
        return super.describe() + " + Sugar";
    }
}