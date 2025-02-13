package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // Special case
        if (root == null || root.left == null) {
            return root;
        }
        // The left node will become the new root
        final TreeNode newRoot = upsideDownBinaryTree(root.left);
        // Make current root as new right child
        root.left.right = root;
        // Make current right as the left child
        root.left.left = root.right;
        // Unlink left and right children
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public static void main(String[] args) {
        final BinaryTreeUpsideDown binaryTreeUpsideDown = new BinaryTreeUpsideDown();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        TreeNode upsideDownRoot = binaryTreeUpsideDown.upsideDownBinaryTree(root);
        System.out.println(upsideDownRoot.levelOrderTraversal(upsideDownRoot));

        root = new TreeNode(1);
        upsideDownRoot = binaryTreeUpsideDown.upsideDownBinaryTree(root);
        System.out.println(upsideDownRoot.levelOrderTraversal(upsideDownRoot));
    }
}
