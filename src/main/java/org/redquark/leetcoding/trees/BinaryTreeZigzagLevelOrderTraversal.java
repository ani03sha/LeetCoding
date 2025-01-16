package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // List to store zigzag traversal
        final List<List<Integer>> result = new ArrayList<>();
        // Special case
        if (root == null) {
            return result;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        // Flag to keep track of reverse
        boolean reverse = false;
        // Process all nodes in the queue
        while (!nodes.isEmpty()) {
            final int size = nodes.size();
            // List to store nodes at the current level
            final List<Integer> current = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // Get the node at the front
                final TreeNode node = nodes.remove();
                if (reverse) {
                    current.addFirst(node.val);
                } else {
                    current.add(node.val);
                }
                // Add left and right children to the queue
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
            result.add(current);
            reverse = !reverse;
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
