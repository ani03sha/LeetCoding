package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        // Special case
        if (root == null) {
            return null;
        }
        // Get references to the left and right children
        final TreeNode left = invertTree(root.left);
        final TreeNode right = invertTree(root.right);
        // Invert current node's children
        root.right = left;
        root.left = right;
        return root;
    }

    public static void main(String[] args) {
        final InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        TreeNode answer = invertBinaryTree.invertTree(root);
        System.out.println(root.levelOrderTraversal(answer));
    }
}
