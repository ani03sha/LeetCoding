package org.redquark.leetcoding.dynamicprogramming;

public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Special case
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        // Dimensions of the grid
        final int m = obstacleGrid.length;
        final int n = obstacleGrid[0].length;
        // Lookup table to store unique paths to reach cell i, j
        final int[][] lookup = new int[m][n];
        lookup[0][0] = 1;
        // For the first column
        for (int i = 1; i < m; i++) {
            lookup[i][0] = obstacleGrid[i][0] == 1 ? 0 : lookup[i - 1][0];
        }
        // For the first row
        for (int j = 1; j < n; j++) {
            lookup[0][j] = obstacleGrid[0][j] == 1 ? 0 : lookup[0][j - 1];
        }
        // Process for remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    lookup[i][j] = lookup[i - 1][j] + lookup[i][j - 1];
                }
            }
        }
        return lookup[m - 1][n - 1];
    }

    public static void main(String[] args) {
        final UniquePathsII uniquePathsII = new UniquePathsII();

        int[][] obstacleGrid = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsII.uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {0, 1},
                {0, 0}
        };
        System.out.println(uniquePathsII.uniquePathsWithObstacles(obstacleGrid));
    }
}
