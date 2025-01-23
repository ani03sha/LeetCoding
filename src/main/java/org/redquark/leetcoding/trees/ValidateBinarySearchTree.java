package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private boolean isValidBSTHelper(TreeNode root, double min, double max) {
        // Base case
        if (root == null) {
            return true;
        }
        // BST property violation
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }

    public static void main(String[] args) {
        final ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(validateBinarySearchTree.isValidBST(root));

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(validateBinarySearchTree.isValidBST(root));
    }
}
