package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class KthSmallestElementInABST {

    private int kthSmallestElement;
    private int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        findKthSmallestElement(root);
        return this.kthSmallestElement;
    }

    private void findKthSmallestElement(TreeNode root) {
        // Base case
        if (root == null) {
            throw new IllegalArgumentException("Root of the tree cannot be null");
        }
        // First, we check in the left subtree
        if (root.left != null) {
            findKthSmallestElement(root.left);
        }
        // Decrement the k
        this.k--;
        // If we have reached k == 0, it means we are at kth smalles node
        if (this.k == 0) {
            this.kthSmallestElement = root.val;
            return;
        }
        // If we did not find kth-smallest element in the entire left subtree,
        // we will move in the right subtree
        if (root.right != null) {
            findKthSmallestElement(root.right);
        }
    }

    public static void main(String[] args) {
        final KthSmallestElementInABST kthSmallestElementInABST = new KthSmallestElementInABST();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        int k = 1;
        System.out.println(kthSmallestElementInABST.kthSmallest(root, k));

        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        k = 3;
        System.out.println(kthSmallestElementInABST.kthSmallest(root, k));
    }
}
