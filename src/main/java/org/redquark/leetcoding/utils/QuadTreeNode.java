package org.redquark.leetcoding.utils;

public class QuadTreeNode {

    public boolean val;
    public boolean isLeaf;
    public QuadTreeNode topLeft;
    public QuadTreeNode topRight;
    public QuadTreeNode bottomLeft;
    public QuadTreeNode bottomRight;

    public QuadTreeNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
    }

    public QuadTreeNode(
            boolean val,
            boolean isLeaf,
            QuadTreeNode topLeft,
            QuadTreeNode topRight,
            QuadTreeNode bottomLeft,
            QuadTreeNode bottomRight
    ) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public void printQuadTree(QuadTreeNode node, String indent) {
        if (node == null) {
            return;
        }

        // Print the current node's information
        if (node.isLeaf) {
            System.out.println(indent + "Leaf(val=" + node.val + ")");
        } else {
            System.out.println(indent + "Node(val=" + node.val + ")");
            // Recursively print the children with increased indentation
            printQuadTree(node.topLeft, indent + "  ");
            printQuadTree(node.topRight, indent + "  ");
            printQuadTree(node.bottomLeft, indent + "  ");
            printQuadTree(node.bottomRight, indent + "  ");
        }
    }
}
