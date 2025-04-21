package org.redquark.leetcoding.utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public String levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return "";
        }
        final StringBuilder levelOrderRepresentation = new StringBuilder();
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
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

    public String toString() {
        return String.valueOf(this.val);
    }
}
