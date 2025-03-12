package org.redquark.leetcoding.dynamicprogramming;

public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        // Order of the matrix
        final int n = matrix.length;
        // Now, let's start from second last row and move upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // Now, we need to find the minimum value from the row
                // just beneath the current row
                int minValue = matrix[i + 1][j];
                if (j > 0) {
                    minValue = Math.min(minValue, matrix[i + 1][j - 1]);
                }
                if (j < n - 1) {
                    minValue = Math.min(minValue, matrix[i + 1][j + 1]);
                }
                // Add this minValue to the current cell we are looking at
                matrix[i][j] += minValue;
            }
        }
        // Minimum falling path sum
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minSum = Math.min(minSum, matrix[0][i]);
        }
        return minSum;
    }

    public static void main(String[] args) {
        final MinimumFallingPathSum minimumFallingPathSum = new MinimumFallingPathSum();

        int[][] matrix = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minimumFallingPathSum.minFallingPathSum(matrix));

        matrix = new int[][]{{-19, 57}, {-40, -5}};
        System.out.println(minimumFallingPathSum.minFallingPathSum(matrix));
    }
}
