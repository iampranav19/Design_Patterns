package composite.bad;

import java.util.ArrayList;
import java.util.List;

// BAD: Product and ProductBundle share no common type, so the cart has to be
// List<Object> and every consumer needs an instanceof/cast per case. Adding
// a 3rd kind of cart item means adding a 3rd else-if branch everywhere the
// cart is walked (here, and anywhere else display/pricing logic is repeated).
public class Main {

    public static void main(String[] args) {
        Product book = new Product("Book", 500);
        Product headphones = new Product("Headphones", 1500);
        Product charger = new Product("Charger", 800);
        Product pen = new Product("Pen", 20);
        Product notebook = new Product("Notebook", 60);

        ProductBundle iphoneCombo = new ProductBundle("iPhone Combo Pack");
        iphoneCombo.addProduct(headphones);
        iphoneCombo.addProduct(charger);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(pen);
        schoolKit.addProduct(notebook);

        // Can't do this: iphoneCombo and schoolKit can't be added to a
        // "Mega Bundle" — ProductBundle.addProduct() only accepts Product.

        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        double total = 0;
        System.out.println("Cart Details:\n");

        for (Object item : cart) {
            if (item instanceof Product) {
                ((Product) item).display("  ");
                total += ((Product) item).getPrice();
            } else if (item instanceof ProductBundle) {
                ((ProductBundle) item).display("  ");
                total += ((ProductBundle) item).getPrice();
            }
        }

        System.out.println("\nTotal Price: " + total);
    }
}