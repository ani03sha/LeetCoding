package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        // Special case
        if (root == null) {
            return true;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        boolean end = false; // Once we see a null child, set this to true
        // Perform BFS
        while (!nodes.isEmpty()) {
            final TreeNode node = nodes.remove();
            if (node == null) {
                end = true;
            } else {
                if (end) {
                    // If we have seen null before and now see a non-null
                    return false;
                }
                nodes.offer(node.left);
                nodes.offer(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final CheckCompletenessOfABinaryTree checkCompletenessOfABinaryTree = new CheckCompletenessOfABinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println(checkCompletenessOfABinaryTree.isCompleteTree(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(checkCompletenessOfABinaryTree.isCompleteTree(root));
    }
}
