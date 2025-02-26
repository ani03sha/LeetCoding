package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CousinsInBinaryTree {

    public boolean isCousins(TreeNode root, int x, int y) {
        // Special case
        if (root == null) {
            return false;
        }
        // Map to store node's value and a pair of its parent and depth
        final Map<Integer, int[]> mappings = new HashMap<>();
        // Variable to keep track of depth
        int depth = 0;
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        mappings.put(root.val, new int[]{0, 0});
        // Perform BFS
        while (!nodes.isEmpty()) {
            depth++;
            final int size = nodes.size();
            for (int i = 0; i < size; i++) {
                final TreeNode node = nodes.remove();
                if (node.left != null) {
                    nodes.offer(node.left);
                    mappings.put(node.left.val, new int[]{node.val, depth});
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                    mappings.put(node.right.val, new int[]{node.val, depth});
                }
            }
            // Check if we have encountered x and y
            if (mappings.containsKey(x) && mappings.containsKey(y)) {
                final int[] xPair = mappings.get(x);
                final int[] yPair = mappings.get(y);
                if (xPair[0] != yPair[0] && xPair[1] == yPair[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final CousinsInBinaryTree cousinsInBinaryTree = new CousinsInBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        int x = 4;
        int y = 3;
        System.out.println(cousinsInBinaryTree.isCousins(root, x, y));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        x = 5;
        y = 4;
        System.out.println(cousinsInBinaryTree.isCousins(root, x, y));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        x = 2;
        y = 3;
        System.out.println(cousinsInBinaryTree.isCousins(root, x, y));
    }
}
