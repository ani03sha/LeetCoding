package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // List to store the k-distanced nodes
        final List<Integer> kDistancedNodes = new ArrayList<>();
        // Special case
        if (root == null) {
            return kDistancedNodes;
        }
        // Convert the binary tree into an undirected graph where
        // vertices are the nodes of the tree and edges are the
        // parent child relationships
        final Map<TreeNode, Set<TreeNode>> graph = new HashMap<>();
        convertTreeToGraph(root, null, graph);
        // If target node is not present in the graph
        if (!graph.containsKey(target)) {
            return kDistancedNodes;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        // Set to track visited nodes
        final Set<TreeNode> visited = new HashSet<>();
        // Add root node
        nodes.offer(target);
        visited.add(target);
        while (!nodes.isEmpty()) {
            final int size = nodes.size();
            // If we have found k nodes
            if (k == 0) {
                for (int i = 0; i < size; i++) {
                    kDistancedNodes.add(nodes.remove().val);
                }
                return kDistancedNodes;
            }
            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                final TreeNode node = nodes.remove();
                // Traverse its neighbors
                for (TreeNode neighbor : graph.get(node)) {
                    if (!visited.contains(neighbor)) {
                        nodes.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            k--;
        }
        return kDistancedNodes;
    }

    private void convertTreeToGraph(TreeNode node, TreeNode parent, Map<TreeNode, Set<TreeNode>> graph) {
        // Base case
        if (node == null) {
            return;
        }
        if (!graph.containsKey(node)) {
            graph.put(node, new HashSet<>());
            if (parent != null) {
                graph.get(node).add(parent);
                graph.get(parent).add(node);
            }
            convertTreeToGraph(node.left, node, graph);
            convertTreeToGraph(node.right, node, graph);
        }
    }

    public static void main(String[] args) {
        final AllNodesDistanceKInBinaryTree allNodesDistanceKInBinaryTree = new AllNodesDistanceKInBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        TreeNode target = root.left;
        int k = 2;
        System.out.println(allNodesDistanceKInBinaryTree.distanceK(root, target, k));

        root = new TreeNode(1);
        target = root;
        k = 3;
        System.out.println(allNodesDistanceKInBinaryTree.distanceK(root, target, k));
    }
}
