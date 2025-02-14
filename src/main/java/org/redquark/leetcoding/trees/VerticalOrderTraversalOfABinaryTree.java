package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VerticalOrderTraversalOfABinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // List to store the final vertical order
        final List<List<Integer>> verticalOrder = new ArrayList<>();
        // Special case
        if (root == null) {
            return verticalOrder;
        }
        // Map to store x coordinate as the key and a list of pair of
        // y and value in the node as the value.
        final Map<Integer, List<int[]>> nodeMap = new TreeMap<>();
        // Perform DFS on the root
        dfs(root, 0, 0, nodeMap);
        // Populate the output using the nodeMap
        for (List<int[]> pairs : nodeMap.values()) {
            // Sort this list by their values because the elements of
            // the current list are at the same x coordinate
            pairs.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            final List<Integer> current = new ArrayList<>();
            for (int[] pair : pairs) {
                current.add(pair[1]);
            }
            verticalOrder.add(current);
        }
        return verticalOrder;
    }

    private void dfs(TreeNode root, int x, int y, Map<Integer, List<int[]>> nodeMap) {
        // Base case
        if (root == null) {
            return;
        }
        nodeMap.putIfAbsent(x, new ArrayList<>());
        nodeMap.get(x).add(new int[]{y, root.val});
        // Traverse left and right subtrees
        dfs(root.left, x - 1, y + 1, nodeMap);
        dfs(root.right, x + 1, y + 1, nodeMap);
    }

    public static void main(String[] args) {
        final VerticalOrderTraversalOfABinaryTree verticalOrderTraversalOfABinaryTree = new VerticalOrderTraversalOfABinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(verticalOrderTraversalOfABinaryTree.verticalTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(verticalOrderTraversalOfABinaryTree.verticalTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(verticalOrderTraversalOfABinaryTree.verticalTraversal(root));
    }
}
