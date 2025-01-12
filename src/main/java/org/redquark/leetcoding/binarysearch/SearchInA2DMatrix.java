package org.redquark.leetcoding.binarysearch;

public class SearchInA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Find the row in which target could be present
        int left = 0;
        int right = m - 1;
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (matrix[middle][0] == target) {
                return true;
            } else if (matrix[middle][0] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        // If we have not found the number yet, then we have to find
        // it in the row represented by `left` column
        int row = left - 1;
        if (row < 0) {
            return false;
        }
        // Now, search in the columns
        left = 0;
        right = n - 1;
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (matrix[row][middle] == target) {
                return true;
            } else if (matrix[row][middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final SearchInA2DMatrix searchInA2DMatrix = new SearchInA2DMatrix();

        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        System.out.println(searchInA2DMatrix.searchMatrix(matrix, target));

        matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        target = 13;
        System.out.println(searchInA2DMatrix.searchMatrix(matrix, target));
    }
}
