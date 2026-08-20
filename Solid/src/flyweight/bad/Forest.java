package flyweight.bad;

import java.util.ArrayList;
import java.util.List;

// BAD: every plantTree() call creates a brand new Tree that stores its own
// copy of name/color/texture, even when a million trees share the exact
// same 3 Strings. That's a million redundant (name, color, texture)
// tuples for data that's actually identical across the whole forest.
public class Forest {

    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, name, color, texture);
        trees.add(tree);
    }

    public void drawTree(int index) {
        trees.get(index).draw();
    }

    public int size() {
        return trees.size();
    }
}