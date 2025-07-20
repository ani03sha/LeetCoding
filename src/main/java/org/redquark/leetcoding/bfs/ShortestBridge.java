package org.redquark.leetcoding.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

    public int shortestBridge(int[][] grid) {
        // Order of the grid
        final int n = grid.length;
        // Array to move in four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Find the entry point for the first island
        int x = -1;
        int y = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        // Queue to perform BFS on the cells of first island
        final Queue<int[]> cells = new LinkedList<>();
        // Perform DFS for the first island and fill the queue
        dfs(grid, x, y, n, cells, directions);
        // Perform BFS on the first island's cells to find the shortest
        // distance to the second island
        return bfs(grid, cells, directions, n);
    }

    private void dfs(int[][] grid, int i, int j, int n, Queue<int[]> cells, int[][] directions) {
        // Base case
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        // Add current cell to the queue
        cells.offer(new int[]{i, j});
        // Mark current cells as visited
        grid[i][j] = 2;
        // Move in four directions
        for (int[] direction : directions) {
            final int x = i + direction[0];
            final int y = j + direction[1];
            dfs(grid, x, y, n, cells, directions);
        }
    }

    private int bfs(int[][] grid, Queue<int[]> cells, int[][] directions, int n) {
        // Shortest distance to the second island
        int distance = 0;
        // Perform BFS on the cells
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
                    // Land cell of the second island is found
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
