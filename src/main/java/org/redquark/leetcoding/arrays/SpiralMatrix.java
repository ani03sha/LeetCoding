package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        // List to store spiral order
        final List<Integer> spiral = new ArrayList<>();
        // Special case
        if (matrix == null || matrix.length == 0) {
            return spiral;
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Pointers for keeping track of rows and columns
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        // Traverse all positions in the matrix
        while (spiral.size() < m * n) {
            // Move left to right
            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;
            // Move top to bottom
            for (int i = top; i <= bottom; i++) {
                spiral.add(matrix[i][right]);
            }
            if (spiral.size() >= m * n) {
                return spiral;
            }
            right--;
            // Move right to left
            for (int i = right; i >= left; i--) {
                spiral.add(matrix[bottom][i]);
            }
            bottom--;
            // Move bottom to top
            for (int i = bottom; i >= top; i--) {
                spiral.add(matrix[i][left]);
            }
            left++;
        }
        return spiral;
    }

    public static void main(String[] args) {
        final SpiralMatrix spiralMatrix = new SpiralMatrix();

        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralMatrix.spiralOrder(matrix));

        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralMatrix.spiralOrder(matrix));
    }
}
