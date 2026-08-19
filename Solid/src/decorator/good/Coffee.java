package decorator.good;

public class Coffee implements Beverage {

    @Override
    public String describe() {
        return "Coffee";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}