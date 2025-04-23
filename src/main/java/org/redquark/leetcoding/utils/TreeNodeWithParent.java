package org.redquark.leetcoding.utils;

public class TreeNodeWithParent {

    public int val;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
    public TreeNodeWithParent parent;

    public TreeNodeWithParent(int val) {
        this.val = val;
    }

    public TreeNodeWithParent(int val, TreeNodeWithParent parent) {
        this.val = val;
        this.parent = parent;
    }
}
