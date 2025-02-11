package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class ClosestBinaryTreeSearchValue {

    public int closestValue(TreeNode root, double target) {
        // Closest value
        int closestValue = root.val;
        // Minimum difference between target and current node's value
        double minimumDifference = Double.MAX_VALUE;
        // Process nodes in the tree
        while (root != null) {
            // Find the current difference
            double currentDifference = Math.abs(root.val - target);
            // Update difference, if needed
            if (currentDifference < minimumDifference || (currentDifference == minimumDifference && root.val < closestValue)) {
                closestValue = root.val;
                minimumDifference = currentDifference;
            }
            // If target is more than the value at root
            else if (target > root.val) {
                root = root.right;
            }
            // If target is less than the value at root
            else {
                root = root.left;
            }
        }
        return closestValue;
    }

    public static void main(String[] args) {
        final ClosestBinaryTreeSearchValue closestBinaryTreeSearchValue = new ClosestBinaryTreeSearchValue();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        double target = 3.714286;
        System.out.println(closestBinaryTreeSearchValue.closestValue(root, target));

        root = new TreeNode(1);
        target = 4.428571;
        System.out.println(closestBinaryTreeSearchValue.closestValue(root, target));
    }
}
