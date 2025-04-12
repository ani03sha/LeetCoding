package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return;
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Boolean flags to represent if any element in the first
        // row or first column is zero
        boolean firstRow = false;
        boolean firstColumn = false;
        // Process the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRow = true;
                    }
                    if (j == 0) {
                        firstColumn = true;
                    }
                    // Set the zeroes in the first row and column
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // Again traverse the matrix except first row and first column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColumn) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        final SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();

        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setMatrixZeroes.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));

        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setMatrixZeroes.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
