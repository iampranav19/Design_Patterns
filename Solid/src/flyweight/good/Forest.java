package flyweight.good;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeTypeFactory.getTreeType(name, color, texture);
        trees.add(new Tree(x, y, type));
    }

    public void drawTree(int index) {
        trees.get(index).draw();
    }

    public int size() {
        return trees.size();
    }
}