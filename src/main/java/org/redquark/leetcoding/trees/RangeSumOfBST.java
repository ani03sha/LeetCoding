package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        // Base case
        if (root == null) {
            return 0;
        }
        // If the value at root is less than low, it means both low and
        // high lie in the right subtree of this root
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        // If the value at root is more than high, it means both low and
        // high lie in the left subtree of this root
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        // If we reach here, it means low and high pass through
        // the root node
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    public static void main(String[] args) {
        final RangeSumOfBST rangeSumOfBST = new RangeSumOfBST();

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);
        int low = 7;
        int high = 15;
        System.out.println(rangeSumOfBST.rangeSumBST(root, low, high));

        root.left.left.left = new TreeNode(1);
        root.left.right.left = new TreeNode(6);
        root.right.left = new TreeNode(13);
        low = 6;
        high = 10;
        System.out.println(rangeSumOfBST.rangeSumBST(root, low, high));
    }
}
