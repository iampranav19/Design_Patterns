package flyweight.good;

// The flyweight: intrinsic state (name, color, texture) that's identical
// across many trees, so it's shared instead of copied. It knows nothing
// about position — that's supplied by the caller at draw time.
public class TreeType {

    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " + name);
    }
}