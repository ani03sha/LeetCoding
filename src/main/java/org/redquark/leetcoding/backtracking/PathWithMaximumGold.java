package org.redquark.leetcoding.backtracking;

public class PathWithMaximumGold {

    private int maxGold = 0;

    public int getMaximumGold(int[][] grid) {
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Four directions
        final int[][] directions = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        // Process each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxGold = Math.max(maxGold, dfs(grid, i, j, m, n, directions));
            }
        }
        return maxGold;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n, int[][] directions) {
        // Check for out-of-bounds cells
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        // Current value of gold
        int currentGold = grid[i][j];
        // Mark current cell visited
        grid[i][j] = 0;
        // Calculate gold collected by moving from this cell
        int localGold = currentGold;
        for (int[] direction : directions) {
            final int x = i + direction[0];
            final int y = j + direction[1];
            localGold = Math.max(localGold, currentGold + dfs(grid, x, y, m, n, directions));
        }
        // Restore original value at this cell
        grid[i][j] = currentGold;
        return localGold;
    }

    public static void main(String[] args) {
        final PathWithMaximumGold pathWithMaximumGold = new PathWithMaximumGold();

        int[][] grid = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(pathWithMaximumGold.getMaximumGold(grid));

        pathWithMaximumGold.maxGold = 0;

        grid = new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
        System.out.println(pathWithMaximumGold.getMaximumGold(grid));
    }
}
