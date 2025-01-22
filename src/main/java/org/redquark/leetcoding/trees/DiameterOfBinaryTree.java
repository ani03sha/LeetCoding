package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        // Variable to keep track of max height of the binary tree.
        // Since values in Java is not pass by reference, I am using
        // Array of 1 element to keep track of it
        final int[] diameter = new int[1];
        getHeightAndDiameter(root, diameter);
        return diameter[0];
    }

    private int getHeightAndDiameter(TreeNode root, int[] diameter) {
        // Base case
        if (root == null) {
            return 0;
        }
        // Get left and right heights
        int leftHeight = getHeightAndDiameter(root.left, diameter);
        int rightHeight = getHeightAndDiameter(root.right, diameter);
        // Update, diameter if needed
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        final DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(root));
    }
}
