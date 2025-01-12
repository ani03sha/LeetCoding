package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class MaxDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // Special case
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        final MaxDepthOfBinaryTree maxDepthOfBinaryTree = new MaxDepthOfBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepthOfBinaryTree.maxDepth(root));

        root = new TreeNode(1);
        root.left = new TreeNode(3);
        System.out.println(maxDepthOfBinaryTree.maxDepth(root));
    }
}
