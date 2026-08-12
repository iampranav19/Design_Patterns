package kiss.bad;

import java.util.List;

// BAD: A generic "evaluator" that loops over a list of rules and ANDs them
// together — built for a flexibility (multiple combinable rules) nobody asked
// for. There's still only ever one rule in the list.
public class FreeShippingEvaluator {

    private final List<FreeShippingRule> rules;

    public FreeShippingEvaluator(List<FreeShippingRule> rules) {
        this.rules = rules;
    }

    public boolean evaluate(double orderTotal) {
        for (FreeShippingRule rule : rules) {
            if (!rule.isEligible(orderTotal)) {
                return false;
            }
        }
        return true;
    }
}