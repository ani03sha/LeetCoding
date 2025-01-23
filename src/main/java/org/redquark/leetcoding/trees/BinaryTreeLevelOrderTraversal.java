package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        // List to store the final output
        final List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        // Process all levels
        while (!nodes.isEmpty()) {
            final int size = nodes.size();
            // List to store the current level
            final List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = nodes.remove();
                currentLevel.add(node.val);
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        final BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(binaryTreeLevelOrderTraversal.levelOrder(root));

        root = new TreeNode(1);
        System.out.println(binaryTreeLevelOrderTraversal.levelOrder(root));

        System.out.println(binaryTreeLevelOrderTraversal.levelOrder(null));
    }
}
