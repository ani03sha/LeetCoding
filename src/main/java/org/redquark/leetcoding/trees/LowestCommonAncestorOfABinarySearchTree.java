package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null) {
            return null;
        }
        // Keep on searching in the same subtree until the values at p and q are
        // more or less than the current root
        while ((p.val < root.val && q.val < root.val) || (p.val > root.val && q.val > root.val)) {
            root = p.val < root.val ? root.left : root.right;
        }
        // If p and q are in different subtrees, only root can be their LCA
        return root;
    }

    public static void main(String[] args) {
        final LowestCommonAncestorOfABinarySearchTree lowestCommonAncestorOfABinarySearchTree = new LowestCommonAncestorOfABinarySearchTree();

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.println(lowestCommonAncestorOfABinarySearchTree.lowestCommonAncestor(root, p, q).val);

        q = root.left.right;
        System.out.println(lowestCommonAncestorOfABinarySearchTree.lowestCommonAncestor(root, p, q).val);
    }
}
