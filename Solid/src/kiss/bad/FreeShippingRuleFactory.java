package kiss.bad;

// BAD: A factory... to create ONE kind of rule, with a hardcoded threshold.
public class FreeShippingRuleFactory {

    public static FreeShippingRule createRule(String ruleType) {
        switch (ruleType) {
            case "MINIMUM_ORDER_AMOUNT":
                return new MinimumOrderAmountRule(50.0);
            default:
                throw new IllegalArgumentException("Unknown rule type: " + ruleType);
        }
    }
}