package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Since a null tree is a subtree
        if (subRoot == null) {
            return true;
        }
        // If a tree is null, no other subtree can be its subtree
        if (root == null) {
            return false;
        }
        // If the current values at nodes match and rest of the tree also, matches
        if (root.val == subRoot.val && matchTree(root, subRoot)) {
            return true;
        }
        // Traverse the main tree left and right
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean matchTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return matchTree(a.left, b.left) && matchTree(a.right, b.right);
    }

    public static void main(String[] args) {
        final SubtreeOfAnotherTree subtreeOfAnotherTree = new SubtreeOfAnotherTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        TreeNode subRoot = root.left;
        System.out.println(subtreeOfAnotherTree.isSubtree(root, subRoot));
    }
}
