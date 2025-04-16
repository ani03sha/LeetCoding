package org.redquark.leetcoding.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TreeNodeWithRandom {

    public int val;
    public TreeNodeWithRandom left;
    public TreeNodeWithRandom right;
    public TreeNodeWithRandom random;

    public TreeNodeWithRandom(int val) {
        this.val = val;
    }

    public static String levelOrderTraversal(TreeNodeWithRandom root) {
        if (root == null) {
            return "";
        }
        final StringBuilder levelOrderRepresentation = new StringBuilder();
        final Queue<TreeNodeWithRandom> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNodeWithRandom node = nodes.remove();
                levelOrderRepresentation.append(" ").append(node.val);
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
        }
        return levelOrderRepresentation.toString();
    }

    // Utility to print random links (for validation)
    public static void printRandomLinks(TreeNodeWithRandom root) {
        if (root == null) return;

        final Queue<TreeNodeWithRandom> queue = new LinkedList<>();
        final Set<TreeNodeWithRandom> visited = new HashSet<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNodeWithRandom node = queue.remove();
            if (visited.contains(node)) continue;
            visited.add(node);

            System.out.println("Node " + node.val + " -> Random: " +
                    (node.random != null ? node.random.val : "null"));

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
}
