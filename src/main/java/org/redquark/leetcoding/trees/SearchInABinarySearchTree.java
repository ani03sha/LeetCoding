package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class SearchInABinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        final SearchInABinarySearchTree searchInABinarySearchTree = new SearchInABinarySearchTree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        int val = 2;
        TreeNode result = searchInABinarySearchTree.searchBST(root, val);
        if (result != null) {
            System.out.println("Found node with value: " + result.val);
        } else {
            System.out.println("Node not found");
        }
    }
}
