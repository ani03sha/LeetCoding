package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        // List to store the final output
        final List<List<Integer>> verticalTraversal = new ArrayList<>();
        // Special case
        if (root == null) {
            return verticalTraversal;
        }
        // Map to keep track of current column and elements at those columns
        final Map<Integer, List<Integer>> mappings = new HashMap<>();
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        // Queue to keep track of column of the current node
        final Queue<Integer> columns = new LinkedList<>();
        nodes.offer(root);
        columns.offer(0);
        // Max and min index
        int maxIndex = 0;
        int minIndex = 0;
        // Process the tree
        while (!nodes.isEmpty()) {
            // Get the node at the front
            final TreeNode node = nodes.remove();
            final int column = columns.remove();
            mappings.putIfAbsent(column, new ArrayList<>());
            mappings.get(column).add(node.val);
            // Add left and right children, if any
            if (node.left != null) {
                nodes.offer(node.left);
                columns.add(column - 1);
                minIndex = Math.min(minIndex, column - 1);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                columns.add(column + 1);
                maxIndex = Math.max(maxIndex, column + 1);
            }
        }
        // Prepare the final output
        for (int i = minIndex; i <= maxIndex; i++) {
            verticalTraversal.add(mappings.get(i));
        }
        return verticalTraversal;
    }

    public static void main(String[] args) {
        final BinaryTreeVerticalOrderTraversal binaryTreeVerticalOrderTraversal = new BinaryTreeVerticalOrderTraversal();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(binaryTreeVerticalOrderTraversal.verticalOrder(root));

        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(7);
        System.out.println(binaryTreeVerticalOrderTraversal.verticalOrder(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);
        System.out.println(binaryTreeVerticalOrderTraversal.verticalOrder(root));
    }
}
