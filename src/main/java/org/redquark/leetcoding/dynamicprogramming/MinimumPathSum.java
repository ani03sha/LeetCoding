package org.redquark.leetcoding.dynamicprogramming;

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Lookup table to store the maximum path sum until the current cell
        final int[][] lookup = new int[m][n];
        lookup[0][0] = grid[0][0];
        // For the first column
        for (int i = 1; i < m; i++) {
            lookup[i][0] = lookup[i - 1][0] + grid[i][0];
        }
        // For the first cell
        for (int j = 1; j < n; j++) {
            lookup[0][j] = lookup[0][j - 1] + grid[0][j];
        }
        // Remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                lookup[i][j] = grid[i][j] + Math.min(lookup[i - 1][j], lookup[i][j - 1]);
            }
        }
        return lookup[m - 1][n - 1];
    }

    public static void main(String[] args) {
        final MinimumPathSum minimumPathSum = new MinimumPathSum();

        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minimumPathSum.minPathSum(grid));

        grid = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(minimumPathSum.minPathSum(grid));
    }
}
