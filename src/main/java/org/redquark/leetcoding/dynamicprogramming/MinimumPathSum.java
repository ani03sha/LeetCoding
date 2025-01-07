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
        // Lookup table to store minimum path sum for a cell i, j
        final int[][] lookup = new int[m][n];
        // For the first column
        int rowSum = 0;
        for (int i = 0; i < m; i++) {
            rowSum += grid[i][0];
            lookup[i][0] = rowSum;
        }
        // For the first row
        int columnSum = 0;
        for (int j = 0; j < n; j++) {
            columnSum += grid[0][j];
            lookup[0][j] = columnSum;
        }
        // For the remaining positions
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                lookup[i][j] = Math.min(lookup[i - 1][j], lookup[i][j - 1]) + grid[i][j];
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
