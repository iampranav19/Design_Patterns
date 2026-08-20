package flyweight.good;

// Only extrinsic state lives here: position. Appearance is a shared
// TreeType reference, not a copy.
public class Tree {

    private final int x;
    private final int y;
    private final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}