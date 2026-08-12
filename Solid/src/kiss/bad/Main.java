package kiss.bad;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FreeShippingRule rule = FreeShippingRuleFactory.createRule("MINIMUM_ORDER_AMOUNT");
        FreeShippingEvaluator evaluator = new FreeShippingEvaluator(List.of(rule));

        double orderTotal = 62.0;
        // An interface, a factory, and an evaluator across 4 files — all to
        // answer one question: "is orderTotal >= 50?"
        System.out.println("Order total $" + orderTotal + " eligible for free shipping? "
                + evaluator.evaluate(orderTotal));
    }
}