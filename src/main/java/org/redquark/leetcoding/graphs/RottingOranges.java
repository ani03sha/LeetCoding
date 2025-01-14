package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Queue to store all fresh oranges
        final Deque<int[]> rottenOranges = new ArrayDeque<>();
        // Count of fresh oranges
        int freshOranges = 0;
        // Total number of minutes elapsed
        int minutes = 0;
        // Process all cells in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    rottenOranges.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        if (freshOranges == 0) {
            return 0;
        }
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // Process cells in the queue
        while (!rottenOranges.isEmpty()) {
            minutes++;
            final int size = rottenOranges.size();
            for (int i = 0; i < size; i++) {
                // Current cell
                final int[] cell = rottenOranges.remove();
                // Check in all four directions
                for (int[] direction : directions) {
                    final int x = cell[0] + direction[0];
                    final int y = cell[1] + direction[1];
                    // Check for the validity of neighboring cells
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                        continue;
                    }
                    rottenOranges.offer(new int[]{x, y});
                    grid[x][y] = 2;
                    freshOranges--;
                }
            }
        }
        return freshOranges != 0 ? -1 : minutes - 1;
    }

    public static void main(String[] args) {
        final RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(rottingOranges.orangesRotting(grid));

        grid = new int[][]{{2, 1, 1}, {0, 0, 1}, {1, 0, 1}};
        System.out.println(rottingOranges.orangesRotting(grid));

        grid = new int[][]{{0, 2}};
        System.out.println(rottingOranges.orangesRotting(grid));
    }
}
