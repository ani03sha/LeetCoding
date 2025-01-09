package org.redquark.leetcoding.graphs;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Array to keep track of visited cells
        final boolean[][] visited = new boolean[m][n];
        // Maximum area
        int maxArea = 0;
        // Process every cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j, m, n, visited));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }
        // Mark current cell visited
        visited[i][j] = true;
        // Perform DFS in all four directions
        return 1 + dfs(grid, i + 1, j, m, n, visited)
                + dfs(grid, i - 1, j, m, n, visited)
                + dfs(grid, i, j + 1, m, n, visited)
                + dfs(grid, i, j - 1, m, n, visited);
    }

    public static void main(String[] args) {
        final MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();

        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));

        grid = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
