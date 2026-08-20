package flyweight.bad;

public class Tree {

    private final int x;
    private final int y;
    private final String name;
    private final String color;
    private final String texture;

    public Tree(int x, int y, String name, String color, String texture) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw() {
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " + name);
    }
}