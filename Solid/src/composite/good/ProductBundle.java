package composite.good;

import java.util.ArrayList;
import java.util.List;

// GOOD: the composite. It holds CartItems, not Products — so a bundle can
// contain other bundles, and getPrice()/display() recurse through however
// deep the tree goes, with no instanceof anywhere.
public class ProductBundle implements CartItem {

    private final String bundleName;
    private final List<CartItem> items = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName);
        for (CartItem item : items) {
            item.display(indent + "  ");
        }
    }
}