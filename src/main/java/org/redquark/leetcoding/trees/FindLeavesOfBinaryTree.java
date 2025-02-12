package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        // List to store the final output
        final List<List<Integer>> leaves = new ArrayList<>();
        // Perform DFS
        dfs(root, leaves);
        return leaves;
    }

    private int dfs(TreeNode node, List<List<Integer>> leaves) {
        // Base case
        if (node == null) {
            return -1;
        }
        // Get left level and right level of the leaves for the current node
        final int leftLevel = dfs(node.left, leaves);
        final int rightLevel = dfs(node.right, leaves);
        // The current level will be 1 plus maximum of leftLevel and rightLevel
        final int currentLevel = 1 + Math.max(leftLevel, rightLevel);
        // Add a list to store leaf nodes at the current level
        while (leaves.size() <= currentLevel) {
            leaves.add(new ArrayList<>());
        }
        leaves.get(currentLevel).add(node.val);
        return currentLevel;
    }

    public static void main(String[] args) {
        final FindLeavesOfBinaryTree findLeavesOfBinaryTree = new FindLeavesOfBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(findLeavesOfBinaryTree.findLeaves(root));

        root = new TreeNode(1);
        System.out.println(findLeavesOfBinaryTree.findLeaves(root));
    }
}
