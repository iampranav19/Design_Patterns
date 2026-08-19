package composite.good;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Product book = new Product("Book", 500);
        Product headphones = new Product("Headphones", 1500);
        Product charger = new Product("Charger", 800);
        Product pen = new Product("Pen", 20);
        Product notebook = new Product("Notebook", 60);

        ProductBundle iphoneCombo = new ProductBundle("iPhone Combo Pack");
        iphoneCombo.addItem(headphones);
        iphoneCombo.addItem(charger);

        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addItem(pen);
        schoolKit.addItem(notebook);

        // A bundle of bundles — impossible in composite.bad, since
        // ProductBundle.addProduct() only accepts a Product there.
        ProductBundle megaBundle = new ProductBundle("Mega Bundle");
        megaBundle.addItem(iphoneCombo);
        megaBundle.addItem(schoolKit);

        List<CartItem> cart = new ArrayList<>();
        cart.add(book);
        cart.add(megaBundle);

        double total = 0;
        System.out.println("Cart Details:\n");

        for (CartItem item : cart) {
            item.display("  ");
            total += item.getPrice();
        }

        System.out.println("\nTotal Price: " + total);
    }
}