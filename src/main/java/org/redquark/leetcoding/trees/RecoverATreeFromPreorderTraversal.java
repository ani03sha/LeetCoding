package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class RecoverATreeFromPreorderTraversal {

    public TreeNode recoverFromPreorder(String traversal) {
        final int n = traversal.length();
        // Map to store the level and the root node at the level
        final Map<Integer, TreeNode> levelNodeMappings = new HashMap<>();
        // Process through the string
        int index = 0;
        while (index < n) {
            // Variables to keep track of dashes and value for the node
            int dashCount = 0;
            int value = 0;
            // Count the dashes
            while (index < n && traversal.charAt(index) == '-') {
                dashCount++;
                index++;
            }
            // Calculate the value to be stored in the node
            while (index < n && Character.isDigit(traversal.charAt(index))) {
                value = value * 10 + traversal.charAt(index) - '0';
                index++;
            }
            // Create a node with this value
            final TreeNode root = new TreeNode(value);
            // Create the mapping in the map
            levelNodeMappings.put(dashCount, root);
            if (dashCount > 0) {
                final TreeNode parent = levelNodeMappings.get(dashCount - 1);
                if (parent.left == null) {
                    parent.left = root;
                } else {
                    parent.right = root;
                }
            }
        }
        // Return root node
        return levelNodeMappings.get(0);
    }

    public static void main(String[] args) {
        final RecoverATreeFromPreorderTraversal recoverATreeFromPreorderTraversal = new RecoverATreeFromPreorderTraversal();

        String traversal = "1-2--3--4-5--6--7";
        TreeNode root = recoverATreeFromPreorderTraversal.recoverFromPreorder(traversal);
        System.out.println(root.levelOrderTraversal(root));

        traversal = "1-2--3---4-5--6---7";
        root = recoverATreeFromPreorderTraversal.recoverFromPreorder(traversal);
        System.out.println(root.levelOrderTraversal(root));

        traversal = "1-401--349---90--88";
        root = recoverATreeFromPreorderTraversal.recoverFromPreorder(traversal);
        System.out.println(root.levelOrderTraversal(root));
    }
}
