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
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        // Second minimum (We are using wrapper class so that I can assign null)
        Integer secondMinimum = null;
        // Process all the levels in the tree
        while (!nodes.isEmpty()) {
            final TreeNode node = nodes.remove();
            if (node.val != root.val) {
                if (secondMinimum == null) {
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
        return secondMinimum == null ? -1 : secondMinimum;
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
