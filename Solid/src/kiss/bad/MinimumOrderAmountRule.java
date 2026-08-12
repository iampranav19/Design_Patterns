package kiss.bad;

public class MinimumOrderAmountRule implements FreeShippingRule {

    private final double minimumAmount;

    public MinimumOrderAmountRule(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    @Override
    public boolean isEligible(double orderTotal) {
        return orderTotal >= minimumAmount;
    }
}