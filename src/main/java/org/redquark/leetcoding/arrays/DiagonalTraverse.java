package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Array to store diagonal order
        final int[] diagonal = new int[m * n];
        // Pointers to traverse through rows and columns
        int i = 0;
        int j = 0;
        // Index for diagonal array
        int index = 0;
        // Process the matrix
        while (i < m && j < n) {
            // Moving up
            while (i >= 0 && j < n) {
                diagonal[index] = matrix[i][j];
                index++;
                i--;
                j++;
            }
            i++;
            // If reached at the end of the column
            if (j == n) {
                i++;
                j--;
            }
            // Moving down
            while (j >= 0 && i < m) {
                diagonal[index] = matrix[i][j];
                index++;
                i++;
                j--;
            }
            j++;
            // If reached at the end of the row
            if (i == m) {
                i--;
                j++;
            }
        }
        return diagonal;
    }

    public static void main(String[] args) {
        final DiagonalTraverse diagonalTraverse = new DiagonalTraverse();

        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(diagonalTraverse.findDiagonalOrder(matrix)));

        matrix = new int[][]{{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(diagonalTraverse.findDiagonalOrder(matrix)));
    }
}
