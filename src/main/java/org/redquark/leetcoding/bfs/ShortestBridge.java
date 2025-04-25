package org.redquark.leetcoding.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

    public int shortestBridge(int[][] grid) {
        // Order of the grid
        final int n = grid.length;
        // Directions array
        final int[][] directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        // Entry point of first island
        int x = -1;
        int y = -1;
        // Find the out the entry point for the first island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        // Queue to store cells of first island
        final Queue<int[]> cells = new LinkedList<>();
        // Now, perform DFS here to mark all the cells of the
        // first island
        dfs(grid, x, y, n, directions, cells);
        // Now, the first island is visited, we will perform
        // BFS to find shortest distance with the second island
        return bfs(grid, cells, directions, n);
    }

    private int bfs(int[][] grid, Queue<int[]> cells, int[][] directions, int n) {
        int distance = 0;
        while (!cells.isEmpty()) {
            final int size = cells.size();
            for (int i = 0; i < size; i++) {
                final int[] cell = cells.remove();
                // Check in all four directions
                for (int[] direction : directions) {
                    final int x = cell[0] + direction[0];
                    final int y = cell[1] + direction[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == -1) {
                        continue;
                    }
                    if (grid[x][y] == 1) {
                        return distance;
                    } else if (grid[x][y] == 0) {
                        cells.offer(new int[]{x, y});
                        grid[x][y] = -1;
                    }
                }
            }
            distance++;
        }
        return distance;
    }

    private void dfs(int[][] grid, int i, int j, int n, int[][] directions, Queue<int[]> cells) {
        // Base case
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        // Mark this cell visited
        grid[i][j] = 2;
        cells.offer(new int[]{i, j});
        // Process all four directions
        for (int[] direction : directions) {
            final int x = i + direction[0];
            final int y = j + direction[1];
            dfs(grid, x, y, n, directions, cells);
        }
    }

    public static void main(String[] args) {
        final ShortestBridge shortestBridge = new ShortestBridge();

        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        System.out.println(shortestBridge.shortestBridge(grid));

        grid = new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        System.out.println(shortestBridge.shortestBridge(grid));

        grid = new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        System.out.println(shortestBridge.shortestBridge(grid));
    }
}
