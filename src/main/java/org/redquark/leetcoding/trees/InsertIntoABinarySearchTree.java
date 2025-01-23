package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class InsertIntoABinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Special case
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        final InsertIntoABinarySearchTree insertIntoABinarySearchTree = new InsertIntoABinarySearchTree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right= new TreeNode(3);
        int val = 5;
        TreeNode newRoot = insertIntoABinarySearchTree.insertIntoBST(root, val);
        System.out.println(newRoot.levelOrderTraversal(newRoot));
    }
}
