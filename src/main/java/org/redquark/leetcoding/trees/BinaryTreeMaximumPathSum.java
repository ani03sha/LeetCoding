package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class BinaryTreeMaximumPathSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return maxSum;
    }

    private int findMaxPathSum(TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }
        // Find max path sum for the left and right subtrees
        final int leftPathSum = Math.max(0, findMaxPathSum(root.left));
        final int rightPathSum = Math.max(0, findMaxPathSum(root.right));
        // Update maxSum, if needed
        maxSum = Math.max(maxSum, leftPathSum + rightPathSum + root.val);
        return root.val + Math.max(leftPathSum, rightPathSum);
    }

    public static void main(String[] args) {
        final BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(binaryTreeMaximumPathSum.maxPathSum(root));

        root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(binaryTreeMaximumPathSum.maxPathSum(root));
    }
}
