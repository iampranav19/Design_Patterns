package flyweight.bad;

public class Main {

    public static void main(String[] args) {
        Forest forest = new Forest();

        for (int i = 0; i < 1_000_000; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }

        System.out.println("Planted " + forest.size() + " trees.");
        forest.drawTree(0);
        forest.drawTree(999_999);
        // 1,000,000 Tree objects, each holding its own "Oak"/"Green"/"Rough"
        // — the exact same 3 Strings, duplicated a million times over.
    }
}