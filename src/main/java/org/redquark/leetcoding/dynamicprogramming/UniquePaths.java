package org.redquark.leetcoding.dynamicprogramming;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // Lookup table to store unique paths to reach a cell i, j
        final int[][] lookup = new int[m][n];
        // Populate the lookup table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    lookup[i][j] = 1;
                } else {
                    lookup[i][j] += lookup[i - 1][j] + lookup[i][j - 1];
                }
            }
        }
        return lookup[m - 1][n - 1];
    }

    public static void main(String[] args) {
        final UniquePaths uniquePaths = new UniquePaths();

        System.out.println(uniquePaths.uniquePaths(3, 7));
        System.out.println(uniquePaths.uniquePaths(3, 2));

    }
}
