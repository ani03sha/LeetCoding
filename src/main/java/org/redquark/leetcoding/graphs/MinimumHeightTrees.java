package org.redquark.leetcoding.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // List to store nodes of minimum height trees
        final List<Integer> mht = new ArrayList<>();
        // Special case
        if (n == 1) {
            mht.add(0);
            return mht;
        }
        // Adjacency list
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Array to keep track of connections from nodes (indegree + outdegree)
        final int[] connections = new int[n];
        // Process all edges
        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            connections[u]++;
            connections[v]++;
        }
        // Queue to store leaves - nodes with only one connection
        final Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (connections[i] == 1) {
                leaves.offer(i);
            }
        }
        // Process until there is one or two nodes left
        while (!leaves.isEmpty()) {
            // Clear previous entries
            mht.clear();
            // Count of leaves at current level
            final int leafCount = leaves.size();
            for (int i = 0; i < leafCount; i++) {
                final int leaf = leaves.remove();
                mht.add(leaf);
                for (int neighbor : graph.get(leaf)) {
                    connections[neighbor]--;
                    if (connections[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }
        return mht;
    }

    public static void main(String[] args) {
        final MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();

        int n = 4;
        int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        System.out.println(minimumHeightTrees.findMinHeightTrees(n, edges));

        n = 6;
        edges = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(minimumHeightTrees.findMinHeightTrees(n, edges));
    }
}
