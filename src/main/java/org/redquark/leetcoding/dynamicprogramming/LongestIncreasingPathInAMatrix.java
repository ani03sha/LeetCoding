package org.redquark.leetcoding.dynamicprogramming;

public class LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        // Special case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        // Dimensions of the matrix
        final int m = matrix.length;
        final int n = matrix[0].length;
        // Lookup table to store longest length at current cell
        final int[][] lookup = new int[m][n];
        // Four directions
        final int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        // Longest length
        int longestLength = 0;
        // Process for every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                longestLength = Math.max(longestLength, dfs(matrix, i, j, m, n, lookup, directions));
            }
        }
        return longestLength;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] lookup, int[][] directions) {
        if (lookup[i][j] != 0) {
            return lookup[i][j];
        }
        // Longest length at the current cell
        int currentLongestLength = 0;
        // Check in all four directions
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                currentLongestLength = Math.max(currentLongestLength, dfs(matrix, x, y, m, n, lookup, directions));
            }
        }
        return lookup[i][j] = 1 + currentLongestLength;
    }

    public static void main(String[] args) {
        final LongestIncreasingPathInAMatrix longestIncreasingPathInAMatrix = new LongestIncreasingPathInAMatrix();

        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPathInAMatrix.longestIncreasingPath(matrix));

        matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(longestIncreasingPathInAMatrix.longestIncreasingPath(matrix));

        matrix = new int[][]{{1}};
        System.out.println(longestIncreasingPathInAMatrix.longestIncreasingPath(matrix));
    }
}
