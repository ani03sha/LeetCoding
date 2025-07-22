package org.redquark.leetcoding.dfs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {

    public int numDistinctIslands(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        // Set to store all the paths
        final Set<String> paths = new HashSet<>();
        // Four directions
        final int[][] direction = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        // Process every cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Process only land cells
                if (grid[i][j] == 1) {
                    final StringBuilder path = new StringBuilder();
                    explorePath(grid, i, j, 0, 0, m, n, direction, path);
                    paths.add(path.toString());
                }
            }
        }
        return paths.size();
    }

    private void explorePath(int[][] grid, int i, int j, int x, int y, int m, int n, int[][] directions, StringBuilder path) {
        // Mark the current cell visited
        grid[i][j] = 0;
        path.append(x).append(",").append(y).append(";");
        // Check in all four directions
        for (int[] direction : directions) {
            int newX = i + direction[0];
            int newY = j + direction[1];
            // Check for the validity of this cell
            if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] == 0) {
                continue;
            }
            explorePath(grid, newX, newY, x + direction[0], y + direction[1], m, n, directions, path);
        }
    }

    public static void main(String[] args) {
        final NumberOfDistinctIslands numberOfDistinctIslands = new NumberOfDistinctIslands();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0}
        };
        System.out.println(numberOfDistinctIslands.numDistinctIslands(grid1)); // Output: 2

        int[][] grid2 = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };
        System.out.println(numberOfDistinctIslands.numDistinctIslands(grid2)); // Output: 2

    }
}
