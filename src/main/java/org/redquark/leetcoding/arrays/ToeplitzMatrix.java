package org.redquark.leetcoding.arrays;

public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        // Process for every element in the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0 && j > 0 && matrix[i - 1][j - 1] != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final ToeplitzMatrix toeplitzMatrix = new ToeplitzMatrix();

        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        System.out.println(toeplitzMatrix.isToeplitzMatrix(matrix));

        matrix = new int[][]{{1, 2}, {2, 2}};
        System.out.println(toeplitzMatrix.isToeplitzMatrix(matrix));
    }
}
