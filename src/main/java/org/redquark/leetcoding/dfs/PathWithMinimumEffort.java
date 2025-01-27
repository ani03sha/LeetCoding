package org.redquark.leetcoding.dfs;

public class PathWithMinimumEffort {

    public int minimumEffortPath(int[][] heights) {
        // Special case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // Dimensions of the trek
        final int m = heights.length;
        final int n = heights[0].length;
        // Minimum effort
        int minimumEffort = Integer.MAX_VALUE;
        // Start and end pointers for minimum effort
        int start = 0;
        int end = Integer.MAX_VALUE;
        // Process until we find suitable minimum effort
        while (start <= end) {
            final int middle = start + (end - start) / 2;
            // Boolean array to store visited nodes
            final boolean[][] visited = new boolean[100][100];
            // Check paths for this value of middle
            if (dfs(heights, 0, 0, middle, m, n, visited)) {
                minimumEffort = Math.min(minimumEffort, middle);
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return minimumEffort;
    }

    private boolean dfs(int[][] heights, int i, int j, int maximumPermissibleDifference, int m, int n, boolean[][] visited) {
        // If we have reached the last cell
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        // Mark the current cell as visited
        visited[i][j] = true;
        // Flag to check if current path is a viable option
        boolean viableOption = false;
        // Perform DFS in all four directions
        if (i > 0 && !visited[i - 1][j] && Math.abs(heights[i][j] - heights[i - 1][j]) <= maximumPermissibleDifference) {
            viableOption = viableOption || dfs(heights, i - 1, j, maximumPermissibleDifference, m, n, visited);
        }
        if (i < m - 1 && !visited[i + 1][j] && Math.abs(heights[i][j] - heights[i + 1][j]) <= maximumPermissibleDifference) {
            viableOption = viableOption || dfs(heights, i + 1, j, maximumPermissibleDifference, m, n, visited);
        }
        if (j > 0 && !visited[i][j - 1] && Math.abs(heights[i][j] - heights[i][j - 1]) <= maximumPermissibleDifference) {
            viableOption = viableOption || dfs(heights, i, j - 1, maximumPermissibleDifference, m, n, visited);
        }
        if (j < n - 1 && !visited[i][j + 1] && Math.abs(heights[i][j] - heights[i][j + 1]) <= maximumPermissibleDifference) {
            viableOption = viableOption || dfs(heights, i, j + 1, maximumPermissibleDifference, m, n, visited);
        }
        return viableOption;
    }

    public static void main(String[] args) {
        final PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();

        int[][] heights = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));

        heights = new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));

        heights = new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
    }
}
