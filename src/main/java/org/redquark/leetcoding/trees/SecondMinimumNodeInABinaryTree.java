package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SecondMinimumNodeInABinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        // Special case
        if (root == null) {
            return -1;
        }
        // Queue to perform the BFS on the tree
        final Queue<TreeNode> nodes = new LinkedList<>();
        // Add root to the queue
        nodes.offer(root);
        // Variable to keep track of second minimum value
        int secondMinimum = -1;
        // Perform BFS
        while (!nodes.isEmpty()) {
            final TreeNode node = nodes.remove();
            // Since node is always the minimum, if current node's value
            // is not equal to the root's value, it can be a candidate of
            // the second minimum value
            if (node.val != root.val) {
                if (secondMinimum == -1) {
                    secondMinimum = node.val;
                } else {
                    secondMinimum = Math.min(secondMinimum, node.val);
                }
            }
            if (node.left != null) {
                nodes.offer(node.left);
            }
            if (node.right != null) {
                nodes.offer(node.right);
            }
        }
        return secondMinimum;
    }

    public static void main(String[] args) {
        final SecondMinimumNodeInABinaryTree secondMinimumNodeInABinaryTree = new SecondMinimumNodeInABinaryTree();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(secondMinimumNodeInABinaryTree.findSecondMinimumValue(root));

        root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(secondMinimumNodeInABinaryTree.findSecondMinimumValue(root));
    }
}
