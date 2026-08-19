package decorator.bad;

public class CoffeeWithMilkAndSugarAndWhip extends CoffeeWithMilkAndSugar {

    @Override
    public double cost() {
        return super.cost() + 0.75;
    }

    @Override
    public String describe() {
        return super.describe() + " + Whip";
    }
}