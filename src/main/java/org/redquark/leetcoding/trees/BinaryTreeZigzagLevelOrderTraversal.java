package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // List to store the final output
        final List<List<Integer>> result = new ArrayList<>();
        // Special case
        if (root == null) {
            return result;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        // Flag to check if reverse traversal is needed
        boolean isReverseTraversal = true;
        // Process all nodes in the queue
        while (!nodes.isEmpty()) {
            // Process all nodes at the current level
            final int size = nodes.size();
            // List to store nodes at the current level
            final List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = nodes.remove();
                if (isReverseTraversal) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.addFirst(node.val);
                }
                // Add both children to the queue, if present
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
            // Toggle flag
            isReverseTraversal = !isReverseTraversal;
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        final BinaryTreeZigzagLevelOrderTraversal binaryTreeZigzagLevelOrderTraversal = new BinaryTreeZigzagLevelOrderTraversal();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root));

        root = new TreeNode(1);
        System.out.println(binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root));

        System.out.println(binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root));
    }
}
