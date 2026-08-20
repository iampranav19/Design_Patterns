package flyweight.good;

public class Main {

    public static void main(String[] args) {
        Forest forest = new Forest();

        for (int i = 0; i < 1_000_000; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }

        System.out.println("Planted " + forest.size() + " trees.");
        forest.drawTree(0);
        forest.drawTree(999_999);
        // Same 1,000,000 trees as flyweight.bad, but only 1 TreeType object
        // was ever created for them — every tree just holds a reference to
        // it, not its own copy of "Oak"/"Green"/"Rough".
        System.out.println("Distinct TreeType objects created: " + TreeTypeFactory.getCreatedTypeCount());
    }
}