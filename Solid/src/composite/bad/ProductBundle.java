package composite.bad;

import java.util.ArrayList;
import java.util.List;

// BAD: a bundle can only hold Products. There's no way to put a bundle
// inside another bundle without a second, near-duplicate class — this
// structure just doesn't nest.
public class ProductBundle {

    private final String bundleName;
    private final List<Product> products = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName);
        for (Product product : products) {
            product.display(indent + "  ");
        }
    }
}