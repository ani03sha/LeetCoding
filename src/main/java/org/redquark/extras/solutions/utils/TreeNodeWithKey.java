package org.redquark.extras.solutions.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a tree node with a key and a value.
 * It can be used in various tree-related algorithms where nodes
 * need to be identified by a unique key.
 */
public class TreeNodeWithKey {

    public String key;
    public int value;
    public List<TreeNodeWithKey> children;

    public TreeNodeWithKey(String key, int value) {
        this.key = key;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public String toString() {
        return this.key + ":" + this.value;
    }

    public void addChild(TreeNodeWithKey node) {
        this.children.add(node);
    }
}
