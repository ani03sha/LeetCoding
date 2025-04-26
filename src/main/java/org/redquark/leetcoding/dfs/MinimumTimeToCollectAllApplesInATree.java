package org.redquark.leetcoding.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumTimeToCollectAllApplesInATree {

    public int minTime(int[][] edges, List<Boolean> hasApple) {
        // Map to create a graph
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            final int a = edge[0];
            final int b = edge[1];
            graph.computeIfAbsent(a, _ -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, _ -> new ArrayList<>()).add(a);
        }
        // Perform DFS on this graph
        return dfs(0, -1, graph, hasApple);
    }

    private int dfs(int node, int parent, Map<Integer, List<Integer>> graph, List<Boolean> hasApple) {
        if (!graph.containsKey(node)) {
            return 0;
        }
        // Total time
        int totalTime = 0;
        // Time for child subtree
        int childTime;
        for (int child : graph.get(node)) {
            if (child == parent) {
                continue;
            }
            childTime = dfs(child, node, graph, hasApple);
            if (childTime > 0 || hasApple.get(child)) {
                totalTime += childTime + 2;
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        final MinimumTimeToCollectAllApplesInATree minimumTimeToCollectAllApplesInATree = new MinimumTimeToCollectAllApplesInATree();

        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> hasApple = List.of(false, false, true, false, true, true, false);
        System.out.println(minimumTimeToCollectAllApplesInATree.minTime(edges, hasApple));

        hasApple = List.of(false, false, true, false, false, true, false);
        System.out.println(minimumTimeToCollectAllApplesInATree.minTime(edges, hasApple));
    }
}
