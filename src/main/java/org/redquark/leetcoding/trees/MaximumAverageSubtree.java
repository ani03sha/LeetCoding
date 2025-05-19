package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class MaximumAverageSubtree {

    // Variable to keep track of maximum average
    private double maxAverage;

    public double maximumAverageSubtree(TreeNode root) {
        // Perform DFS on the tree
        dfs(root);
        return maxAverage;
    }

    private int[] dfs(TreeNode root) {
        // Base case
        if (root == null) {
            return new int[]{0, 0}; // {sum, nodeCount}
        }
        // Traverse left and right subtrees
        final int[] left = dfs(root.left);
        final int[] right = dfs(root.right);
        // Calculate the sum and nodeCount
        final int sum = root.val + left[0] + right[0];
        final int nodeCount = 1 + left[1] + right[1];
        // Update maxAverage, if required
        this.maxAverage = Math.max(this.maxAverage, sum * 1.0 / nodeCount);
        return new int[]{sum, nodeCount};
    }

    public static void main(String[] args) {
        final MaximumAverageSubtree maximumAverageSubtree = new MaximumAverageSubtree();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);
        System.out.println(maximumAverageSubtree.maximumAverageSubtree(root));

        maximumAverageSubtree.maxAverage = 0;
        root = new TreeNode(1);
        System.out.println(maximumAverageSubtree.maximumAverageSubtree(root));
    }
}
