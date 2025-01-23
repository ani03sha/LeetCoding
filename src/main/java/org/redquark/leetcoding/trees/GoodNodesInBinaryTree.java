package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class GoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        return goodNodesHelper(root, Integer.MIN_VALUE);
    }

    private int goodNodesHelper(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int currentCount = 0;
        if (max <= root.val) {
            currentCount++;
        }
        max = Math.max(max, root.val);
        return goodNodesHelper(root.left, max) + goodNodesHelper(root.right, max) + currentCount;
    }

    public static void main(String[] args) {
        final GoodNodesInBinaryTree goodNodesInBinaryTree = new GoodNodesInBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(goodNodesInBinaryTree.goodNodes(root));

        root = new TreeNode(3);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(goodNodesInBinaryTree.goodNodes(root));
    }
}
