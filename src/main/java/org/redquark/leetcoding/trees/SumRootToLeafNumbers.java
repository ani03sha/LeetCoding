package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // For the leaf nodes
        if (root.right == null && root.left == null) {
            return sum * 10 + root.val;
        }
        // For all other nodes
        return dfs(root.left, sum * 10 + root.val) + dfs(root.right, sum * 10 + root.val);
    }

    public static void main(String[] args) {
        final SumRootToLeafNumbers sumRootToLeafNumbers = new SumRootToLeafNumbers();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumRootToLeafNumbers.sumNumbers(root));

        root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        System.out.println(sumRootToLeafNumbers.sumNumbers(root));
    }
}
