package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Count of number of islands
        int islandCount = 0;
        // Array to mark visited cells
        final boolean[][] visited = new boolean[m][n];
        // Process each cell of the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(grid, i, j, m, n, visited);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    private void bfs(char[][] grid, int i, int j, int m, int n, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return;
        }
        // Mark the current cell visited
        visited[i][j] = true;
        // Queue to perform BFS
        final Queue<int[]> nodes = new ArrayDeque<>();
        nodes.offer(new int[]{i, j});
        // Directions array
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!nodes.isEmpty()) {
            int[] node = nodes.remove();
            // Traverse in all four directions
            for (int[] direction : directions) {
                int x = direction[0] + node[0];
                int y = direction[1] + node[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == '1') {
                    visited[x][y] = true;
                    nodes.offer(new int[]{x, y});
                }
            }
        }
    }

    public static void main(String[] args) {
        final NumberOfIslands numberOfIslands = new NumberOfIslands();

        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numberOfIslands.numIslands(grid));

        grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
