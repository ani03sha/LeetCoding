package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class CountNodesEqualToAverageOfSubtree {

    // Count for trees satisfying condition
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return this.count;
    }

    private int[] dfs(TreeNode root) {
        int currentSum = root.val;
        int subtreeCount = 1;
        if (root.left != null) {
            final int[] left = dfs(root.left);
            currentSum += left[0];
            subtreeCount += left[1];
        }
        if (root.right != null) {
            final int[] right = dfs(root.right);
            currentSum += right[0];
            subtreeCount += right[1];
        }
        // Calculate average
        final int average = currentSum / subtreeCount;
        if (average == root.val) {
            this.count++;
        }
        return new int[]{currentSum, subtreeCount};
    }

    public static void main(String[] args) {
        final CountNodesEqualToAverageOfSubtree countNodesEqualToAverageOfSubtree = new CountNodesEqualToAverageOfSubtree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(6);
        System.out.println(countNodesEqualToAverageOfSubtree.averageOfSubtree(root));

        countNodesEqualToAverageOfSubtree.count = 0;

        root = new TreeNode(1);
        System.out.println(countNodesEqualToAverageOfSubtree.averageOfSubtree(root));
    }
}
