package org.redquark.leetcoding.arrays;

public class SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Indices to traverse the matrix
        int i = m - 1;
        int j = 0;
        // Process until we reach the end of the matrix or we
        // find the element
        while (i >= 0 && j < n) {
            if (target < matrix[i][j]) {
                i--;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final SearchA2DMatrixII searchA2DMatrixII = new SearchA2DMatrixII();

        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;
        System.out.println(searchA2DMatrixII.searchMatrix(matrix, target));

        target = 20;
        System.out.println(searchA2DMatrixII.searchMatrix(matrix, target));
    }
}
