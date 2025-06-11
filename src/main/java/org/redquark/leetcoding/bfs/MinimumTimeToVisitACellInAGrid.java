package org.redquark.leetcoding.bfs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumTimeToVisitACellInAGrid {

    public int minimumTime(int[][] grid) {
        // Base case is when we cannot move to neighboring cells
        // from the top-left position
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Min heap stores {time, row, column} -> sorted on time
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Array to store visited cells
        final boolean[][] visited = new boolean[m][n];
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Add the top-left corner to the minHeap
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        // Perform BFS
        while (!minHeap.isEmpty()) {
            final int[] current = minHeap.remove();
            final int time = current[0];
            final int row = current[1];
            final int column = current[2];
            // If we have reached target
            if (row == m - 1 && column == n - 1) {
                return time;
            }
            // If we have already visited this cell
            if (visited[row][column]) {
                continue;
            }
            // Else, mark this cell visited and process further
            visited[row][column] = true;
            // Check in all four directions
            for (int[] direction : directions) {
                final int x = row + direction[0];
                final int y = column + direction[1];
                // Check for the validity of new cell
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }
                // Calculate the wait time needed to move to the next cell
                final int waitTime = (grid[x][y] - time) % 2 == 0 ? 1 : 0;
                final int nextTime = Math.max(grid[x][y] + waitTime, time + 1);
                minHeap.offer(new int[]{nextTime, x, y});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final MinimumTimeToVisitACellInAGrid minimumTimeToVisitACellInAGrid = new MinimumTimeToVisitACellInAGrid();

        int[][] grid = new int[][]{
                {0, 2, 4},
                {3, 2, 1},
                {1, 0, 4}
        };
        System.out.println("Minimum time to visit the cell: " + minimumTimeToVisitACellInAGrid.minimumTime(grid));

        grid = new int[][]{
                {0, 1, 3, 2},
                {5, 1, 2, 5},
                {4, 3, 8, 6}
        };
        System.out.println("Minimum time to visit the cell: " + minimumTimeToVisitACellInAGrid.minimumTime(grid));

    }
}
