package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class ClosestBinaryTreeSearchValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // List to store the k closest values to the target
        final List<Integer> kClosestValues = new LinkedList<>();
        // Special case
        if (root == null) {
            return kClosestValues;
        }
        // Perform inorder traversal on the tree
        inorderTraversal(root, target, k, kClosestValues);
        return kClosestValues;
    }

    private void inorderTraversal(TreeNode root, double target, int k, List<Integer> kClosestValues) {
        if (root == null) {
            return;
        }
        // Perform inorder traversal in the left subtree
        inorderTraversal(root.left, target, k, kClosestValues);
        // If there are less than k elements in the list, we have room to add more
        if (kClosestValues.size() < k) {
            kClosestValues.add(root.val);
        }
        // If there are k or more elements in the list, we will maintain only k elements
        else {
            // If adding the current node's value to the list does not bring it closer to the
            // target, there is no point in traversing further right
            if (Math.abs(root.val - target) >= Math.abs(kClosestValues.getFirst() - target)) {
                return;
            }
            // Remove the farthest element from the list
            kClosestValues.removeFirst();
            // Add the current node to the list
            kClosestValues.add(root.val);
        }
        // Perform inorder traversal in the right subtree
        inorderTraversal(root.right, target, k, kClosestValues);
    }

    public static void main(String[] args) {
        final ClosestBinaryTreeSearchValueII closestBinaryTreeSearchValueII = new ClosestBinaryTreeSearchValueII();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        double target = 3.714286;
        int k = 2;
        System.out.println(closestBinaryTreeSearchValueII.closestKValues(root, target, k));

        root = new TreeNode(1);
        target = 0.000000;
        k = 1;
        System.out.println(closestBinaryTreeSearchValueII.closestKValues(root, target, k));


    }
}
