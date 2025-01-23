package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        // Special case
        if (root == null) {
            return null;
        }
        // If key is smaller than the root's value, we move in left subtree
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        // If key is greater than the root's value, we move in right subtree
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // If we have found the key
        // If the node has no left child, we return right child
        if (root.left == null) {
            return root.right;
        }
        // If node has no right child, we return left child
        if (root.right == null) {
            return root.left;
        }
        // If the node has both children, we will find the successor
        TreeNode successor = root.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        // Move left subtree of node to the left tree of successor
        successor.left = root.left;
        // New root should be right child of deleted node
        root = root.right;
        return root;
    }

    public static void main(String[] args) {
        final DeleteNodeInABST deleteNodeInABST = new DeleteNodeInABST();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        int key = 3;
        root = deleteNodeInABST.deleteNode(root, key);
        System.out.println(root.levelOrderTraversal(root));

        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        key = 0;
        root = deleteNodeInABST.deleteNode(root, key);
        System.out.println(root.levelOrderTraversal(root));
    }
}
