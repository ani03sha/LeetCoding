package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode a, TreeNode b) {
        // Base case
        if (a == null && b == null) {
            return true;
        }
        // For a tree to be symmetric, the following conditions need to be satisfied
        // 1. Data in both nodes that are diametrically opposite should be same
        // 2. Left subtree of the left tree == right subtree of the right tree
        // 3. Right subtree of the left tree == left subtree of the left tree
        if (a != null && b != null && a.val == b.val) {
            return isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left);
        }
        return false;
    }

    public static void main(String[] args) {
        final SymmetricTree symmetricTree = new SymmetricTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(symmetricTree.isSymmetric(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println(symmetricTree.isSymmetric(root));
    }
}
