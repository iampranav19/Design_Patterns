package kiss.bad;

public interface FreeShippingRule {
    boolean isEligible(double orderTotal);
}