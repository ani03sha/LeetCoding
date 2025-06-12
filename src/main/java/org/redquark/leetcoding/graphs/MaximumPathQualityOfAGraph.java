package org.redquark.leetcoding.graphs;

import java.util.ArrayList;
import java.util.List;

public class MaximumPathQualityOfAGraph {

    private int maxQuality;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        // Total number of nodes in the graph
        final int n = values.length;
        // Build a graph
        final List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            final int from = edge[0];
            final int to = edge[1];
            final int time = edge[2];
            graph.get(from).add(new int[]{to, time});
            graph.get(to).add(new int[]{from, time});
        }
        // Array to store visited nodes
        final boolean[] visited = new boolean[n];
        visited[0] = true;
        // Perform DFS starting from 0
        dfs(graph, 0, maxTime, values[0], values, visited);
        return this.maxQuality;
    }

    private void dfs(List<List<int[]>> graph, int currentNode, int remainingTime, int currentQuality, int[] values, boolean[] visited) {
        // We have reached start again, update maxQuality, if required
        if (currentNode == 0 && currentQuality > this.maxQuality) {
            this.maxQuality = currentQuality;
        }
        // Recursively visit neighbors
        for (int[] neighbor : graph.get(currentNode)) {
            final int nextNode = neighbor[0];
            final int time = neighbor[1];
            if (remainingTime >= time) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    dfs(graph, nextNode, remainingTime - time, currentQuality + values[nextNode], values, visited);
                    visited[nextNode] = false;
                } else {
                    dfs(graph, nextNode, remainingTime - time, currentQuality, values, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        final MaximumPathQualityOfAGraph maximumPathQualityOfAGraph = new MaximumPathQualityOfAGraph();

        int[] values = {0, 32, 10, 43};
        int[][] edges = {{0, 1, 10}, {1, 2, 15}, {0, 3, 10}};
        int maxTime = 49;
        int result = maximumPathQualityOfAGraph.maximalPathQuality(values, edges, maxTime);
        System.out.println("Maximum Path Quality: " + result); // Expected output: 75
        maximumPathQualityOfAGraph.maxQuality = 0; // Reset for next test case

        values = new int[]{5, 10, 15, 20};
        edges = new int[][]{{0, 1, 10}, {1, 2, 10}, {0, 3, 10}};
        maxTime = 30;
        result = maximumPathQualityOfAGraph.maximalPathQuality(values, edges, maxTime);
        System.out.println("Maximum Path Quality: " + result); // Expected output: 25
        maximumPathQualityOfAGraph.maxQuality = 0; // Reset for next test case

        values = new int[]{1, 2, 3, 4};
        edges = new int[][]{{0, 1, 10}, {1, 2, 11}, {2, 3, 12}, {1, 3, 13}};
        maxTime = 50;
        result = maximumPathQualityOfAGraph.maximalPathQuality(values, edges, maxTime);
        System.out.println("Maximum Path Quality: " + result); // Expected output: 7
    }
}
