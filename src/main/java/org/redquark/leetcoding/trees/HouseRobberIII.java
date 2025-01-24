package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class HouseRobberIII {

    public int rob(TreeNode root) {
        // We have two cases - either to include the current node
        // or to not include the current node.
        // 1. If current node is included, we cannot include its children
        // 2. If current node is not included, we can include its children

        // Array to keep track of loot amount when node is included and not included
        // [withRoot, withoutRoot]
        final int[] robAmount = robHelper(root);
        return Math.max(robAmount[0], robAmount[1]);
    }

    private int[] robHelper(TreeNode root) {
        // Base case, you cannot rob anything
        if (root == null) {
            return new int[]{0, 0};
        }
        // Recurse for left and right subtrees
        final int[] left = robHelper(root.left);
        final int[] right = robHelper(root.right);
        // Include the current node - current node values + values from its grandchildren
        int withRoot = root.val + left[1] + right[1];
        // Skip the current node - maximum values from children
        int withoutRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{withRoot, withoutRoot};
    }

    public static void main(String[] args) {
        final HouseRobberIII houseRobberIII = new HouseRobberIII();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(houseRobberIII.rob(root));

        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(1);
        System.out.println(houseRobberIII.rob(root));
    }
}
