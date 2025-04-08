package org.redquark.leetcoding.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        // Order of the matrix
        final int n = grid.length;
        // Special case
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        // Directions array
        final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
        // Queue to perform BFS
        final Queue<int[]> cells = new LinkedList<>();
        cells.offer(new int[]{0, 0});
        // Array to mark visited cells
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        // Length of the shortest clear path
        int distance = 0;
        // Perform BFS
        while (!cells.isEmpty()) {
            final int size = cells.size();
            for (int i = 0; i < size; i++) {
                final int[] cell = cells.remove();
                // If we reached the end of the grid
                if (cell[0] == n - 1 && cell[1] == n - 1) {
                    return 1 + distance;
                }
                // Traverse in all eight directions
                for (int[] direction : directions) {
                    final int x = cell[0] + direction[0];
                    final int y = cell[1] + direction[1];
                    // Check for the validity of this cell
                    if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y] || grid[x][y] == 1) {
                        continue;
                    }
                    cells.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            distance++;
        }
        return -1;
    }

    public static void main(String[] args) {
        final ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();

        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));

        grid = new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));

        grid = new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));
    }
}
