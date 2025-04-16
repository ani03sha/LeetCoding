package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNodeNAry;

import java.util.List;

public class DiameterOfNAryTree {

    public int diameter(TreeNodeNAry root) {
        final int[] diameter = new int[1];
        getModifiedHeight(root, diameter);
        return diameter[0];
    }

    private int getModifiedHeight(TreeNodeNAry node, int[] diameter) {
        // Base case
        if (node.children.isEmpty()) {
            return 0;
        }
        // Get top two largest heights
        int maxHeight = 0;
        int secondMaxHeight = 0;
        // Traverse through the children of current node
        for (TreeNodeNAry child : node.children) {
            // Get the height of the parent of the current node
            int parentHeight = 1 + getModifiedHeight(child, diameter);
            if (parentHeight > maxHeight) {
                secondMaxHeight = maxHeight;
                maxHeight = parentHeight;
            } else if (parentHeight > secondMaxHeight) {
                secondMaxHeight = parentHeight;
            }
            // Calculate the distance between two farthest nodes
            final int distance = maxHeight + secondMaxHeight;
            diameter[0] = Math.max(diameter[0], distance);
        }
        return maxHeight;
    }

    public static void main(String[] args) {
        final DiameterOfNAryTree diameterOfNAryTree = new DiameterOfNAryTree();

        TreeNodeNAry one = new TreeNodeNAry(1);
        TreeNodeNAry two = new TreeNodeNAry(2);
        TreeNodeNAry three = new TreeNodeNAry(3);
        TreeNodeNAry four = new TreeNodeNAry(4);
        TreeNodeNAry five = new TreeNodeNAry(5);
        TreeNodeNAry six = new TreeNodeNAry(6);

        one.children = List.of(three, two, four);
        three.children = List.of(five, six);
        System.out.println(diameterOfNAryTree.diameter(one));
    }
}
