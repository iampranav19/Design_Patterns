package decorator.good;

// GOOD: wraps an existing Beverage and adds its own cost/description on top,
// instead of adding a field and an if-branch to Coffee itself. Any decorator
// can wrap any Beverage, including another decorator, so combinations are
// built by composition instead of being hardcoded as booleans.
public abstract class CondimentDecorator implements Beverage {

    protected final Beverage wrapped;

    protected CondimentDecorator(Beverage wrapped) {
        this.wrapped = wrapped;
    }
}