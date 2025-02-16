package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Special case
        if (root == null || root == p || root == q) {
            return root;
        }
        // Recursively call the function for left and right subtrees
        final TreeNode left = lowestCommonAncestor(root.left, p, q);
        final TreeNode right = lowestCommonAncestor(root.right, p, q);
        // Check if we have found the given nodes
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        final LowestCommonAncestorOfABinaryTree lowestCommonAncestorOfABinaryTree = new LowestCommonAncestorOfABinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);

        p = root.left;
        q = root.left.right.right;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        p = root;
        q = root.left;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);
    }
}
