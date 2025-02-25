package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class RecoverBinarySearchTree {

    // Node to represent previous node to the current node
    TreeNode previous = null;
    // Two misplaces nodes
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        // Reference to the root noode
        TreeNode current = root;
        while (current != null) {
            // Check if the left node is null
            if (current.left == null) {
                // Detect misplaced nodes
                detectMisplacedNodes(current);
                // Move to the right
                current = current.right;
            } else {
                // Find the inorder predecessor of the left node
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                // If the right children of predecessor is null, do the threading
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                }
                // Else, restore the tree
                else {
                    // Remove the thread
                    predecessor.right = null;
                    detectMisplacedNodes(current);
                    current = current.right;
                }
            }
        }
        // Swap first and second nodes
        if (first != null && second != null) {
            final int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private void detectMisplacedNodes(TreeNode current) {
        if (previous != null && previous.val > current.val) {
            if (first == null) {
                first = previous;
            }
            second = current;
        }
        previous = current;
    }

    public static void main(String[] args) {
        final RecoverBinarySearchTree recoverBinarySearchTree = new RecoverBinarySearchTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        recoverBinarySearchTree.recoverTree(root);
        System.out.println(root.levelOrderTraversal(root));
    }
}
