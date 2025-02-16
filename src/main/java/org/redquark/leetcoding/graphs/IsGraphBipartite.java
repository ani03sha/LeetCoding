package org.redquark.leetcoding.graphs;

public class IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {
        final int n = graph.length;
        // Array to color the nodes
        // 0 - no color
        // -1 and 1 are two colors
        final int[] colors = new int[n];
        // Color all nodes
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int index, int color) {
        if (colors[index] != 0) {
            return colors[index] != color;
        }
        // Color the current node
        colors[index] = color;
        // Process for all neighbors
        for (int neighbor : graph[index]) {
            if (dfs(graph, colors, neighbor, -color)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final IsGraphBipartite isGraphBipartite = new IsGraphBipartite();

        int[][] graph = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println(isGraphBipartite.isBipartite(graph));

        graph = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(isGraphBipartite.isBipartite(graph));
    }
}
