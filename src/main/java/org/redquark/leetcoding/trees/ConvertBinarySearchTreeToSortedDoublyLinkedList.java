package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    private TreeNode head;
    private TreeNode tail;

    public TreeNode treeToDoublyList(TreeNode root) {
        // Special case
        if (root == null) {
            return null;
        }
        // Perform inorder traversal on the tree
        inorder(root);
        // Make the DLL circular
        tail.right = head;
        head.left = tail;
        return head;
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            // Traverse the left subtree
            inorder(root.left);
            // Process current node
            if (tail != null) {
                tail.right = root;
                root.left = tail;
            } else {
                head = root;
            }
            tail = root;
            // Traverse the right subtree
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        final ConvertBinarySearchTreeToSortedDoublyLinkedList convertBinarySearchTreeToSortedDoublyLinkedList =
                new ConvertBinarySearchTreeToSortedDoublyLinkedList();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode head = convertBinarySearchTreeToSortedDoublyLinkedList.treeToDoublyList(root);

        // Print the doubly linked list in forward direction (circular)
        System.out.println("Forward:");
        TreeNode curr = head;
        for (int i = 0; i < 5; i++) {  // Print 5 nodes
            System.out.print(curr.val + " ");
            curr = curr.right;
        }

        // Print the doubly linked list in backward direction (circular)
        System.out.println("\nBackward:");
        curr = head.left; // tail
        for (int i = 0; i < 5; i++) {
            System.out.print(curr.val + " ");
            curr = curr.left;
        }
    }
}
