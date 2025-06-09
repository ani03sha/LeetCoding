package org.redquark.leetcoding.dynamicprogramming;

public class CherryPickup {

    public int cherryPickup(int[][] grid) {
        // Order of the grid
        final int n = grid.length;
        // Lookup table to store maximum cherries collected by two persons
        // who have moved k steps and at positions (i1, k - j1) and (i2, k - j2).
        // This is represented by lookup[k][i1][i2].
        final int[][][] lookup = new int[2 * n - 1][n][n];
        // Base initialization - 0 steps moved and at (0, 0)
        lookup[0][0][0] = grid[0][0];
        // Traverse over total number of steps
        for (int k = 1; k < 2 * n - 1; k++) {
            // Traverse the grid for person 1 at (i1, j1)
            for (int i1 = 0; i1 < n; i1++) {
                // Traverse the grid for person 2 at (i2, j2)
                for (int i2 = 0; i2 < n; i2++) {
                    int j1 = k - i1;
                    int j2 = k - i2;
                    // Initialize with min value
                    lookup[k][i1][i2] = Integer.MIN_VALUE;
                    // Skip if not a valid cell or a thorn
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1) {
                        continue;
                    }
                    // Cherries collected by person1
                    int cherries = grid[i1][j1];
                    // If both persons are on different cells
                    if (i1 != i2 || j1 != j2) {
                        cherries += grid[i2][j2];
                    }
                    // Check each possibility of a step for both persons, and choose the one which yields max cherries
                    for (int x = i1 - 1; x <= i1; x++) {
                        for (int y = i2 - 1; y <= i2; y++) {
                            if (x >= 0 && y >= 0) {
                                lookup[k][i1][i2] = Math.max(lookup[k][i1][i2], lookup[k - 1][x][y] + cherries);
                            }
                        }
                    }
                }
            }
        }
        return Math.max(0, lookup[2 * n - 2][n - 1][n - 1]);
    }

    public static void main(String[] args) {
        final CherryPickup cherryPickup = new CherryPickup();

        int[][] grid = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        };
        System.out.println(cherryPickup.cherryPickup(grid)); // 5

        grid = new int[][]{
                {1, 1, -1},
                {1, -1, 1},
                {-1, 1, 1}
        };
        System.out.println(cherryPickup.cherryPickup(grid)); // 0

    }
}
