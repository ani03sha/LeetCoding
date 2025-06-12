package org.redquark.leetcoding.graphs;

import java.util.Arrays;

public class NumberOfPossibleSetsOfClosingBranches {

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        // Total number of possible subsets
        int totalSubsets = 0;
        // Check for every subset (2^n-1)
        for (int mask = 0; mask < (1 << n); mask++) {
            // Graph based on the number of nodes
            final int[][] graph = new int[n][n];
            Arrays.stream(graph).forEach(row -> Arrays.fill(row, 1 << 29)); // Default value is infinity
            // Fill the actual values in the graph, wherever possible
            for (int[] road : roads) {
                final int u = road[0];
                final int v = road[1];
                final int w = road[2];
                // Check if u and v are part of the current subset
                if (((mask >> u) & 1) == 1 && ((mask >> v) & 1) == 1) {
                    graph[u][v] = Math.min(graph[u][v], w);
                    graph[v][u] = Math.min(graph[v][u], w);
                }
            }
            // Perform Floyd-Warshall to find all shortest paths in the current subset
            for (int k = 0; k < n; k++) {
                // Check if this intermediate node is part of the current subset
                if (((mask >> k) & 1) == 1) {
                    // The Distance of a node from itself is 0
                    graph[k][k] = 0;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                        }
                    }
                }
            }
            // Check if the current subset is a valid subset
            boolean isValidSubset = true;
            for (int i = 0; i < n && isValidSubset; i++) {
                for (int j = 0; j < n && isValidSubset; j++) {
                    if (((mask >> i) & 1) == 1 && ((mask >> j) & 1) == 1) {
                        if (graph[i][j] > maxDistance) {
                            isValidSubset = false;
                        }
                    }
                }
            }
            totalSubsets += isValidSubset ? 1 : 0;
        }
        return totalSubsets;
    }

    public static void main(String[] args) {
        final NumberOfPossibleSetsOfClosingBranches numberOfPossibleSetsOfClosingBranches = new NumberOfPossibleSetsOfClosingBranches();

        int n = 3;
        int maxDistance = 5;
        int[][] roads = new int[][]{
                {0, 1, 2},
                {1, 2, 10},
                {0, 2, 10}
        };
        int result = numberOfPossibleSetsOfClosingBranches.numberOfSets(n, maxDistance, roads);
        System.out.println("Total number of possible sets of closing branches: " + result);

        n = 3;
        maxDistance = 5;
        roads = new int[][]{
                {0, 1, 20},
                {0, 1, 10},
                {1, 2, 2},
                {0, 2, 2}
        };
        result = numberOfPossibleSetsOfClosingBranches.numberOfSets(n, maxDistance, roads);
        System.out.println("Total number of possible sets of closing branches: " + result);

        n = 1;
        maxDistance = 10;
        roads = new int[][]{};
        result = numberOfPossibleSetsOfClosingBranches.numberOfSets(n, maxDistance, roads);
        System.out.println("Total number of possible sets of closing branches: " + result);
    }
}
