package flyweight.good;

import java.util.HashMap;
import java.util.Map;

// GOOD: the flyweight factory. It hands out the same TreeType for a given
// (name, color, texture), creating a new one only the first time that
// combination is seen — so a million identical trees still share 1
// TreeType instance instead of holding a million copies.
public class TreeTypeFactory {

    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        return treeTypes.computeIfAbsent(key, k -> new TreeType(name, color, texture));
    }

    public static int getCreatedTypeCount() {
        return treeTypes.size();
    }
}