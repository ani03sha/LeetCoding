package org.redquark.leetcoding.dynamicprogramming;

public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Lookup table to store the size of submatrix of
        // which current cell is the bottom right corner
        final int[][] lookup = new int[m + 1][n + 1];
        // Variable to keep track of the biggest square matrix
        // seen so far
        int maxSize = 0;
        // Process every cell in the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    lookup[i][j] = 1 + Math.min(lookup[i - 1][j - 1], Math.min(lookup[i - 1][j], lookup[i][j - 1]));
                    maxSize = Math.max(maxSize, lookup[i][j]);
                }
            }
        }
        return maxSize * maxSize;
    }

    public static void main(String[] args) {
        final MaximalSquare maximalSquare = new MaximalSquare();

        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare.maximalSquare(matrix));

        matrix = new char[][]{{'0', '1'}, {'1', '0'}};
        System.out.println(maximalSquare.maximalSquare(matrix));

        matrix = new char[][]{{'0'}};
        System.out.println(maximalSquare.maximalSquare(matrix));
    }
}
