package org.redquark.leetcoding.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // Dimensions of the maze
        final int m = maze.length;
        final int n = maze[0].length;
        // Queue to perform BFS
        final Queue<int[]> cells = new LinkedList<>();
        // Push start cell in the queue
        cells.offer(start);
        // Visited cells
        final boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Perform BFS
        while (!cells.isEmpty()) {
            // Get the current cell
            final int[] cell = cells.remove();
            if (cell[0] == destination[0] && cell[1] == destination[1]) {
                return true;
            }
            // Check in all four directions
            for (int[] direction : directions) {
                int x = cell[0];
                int y = cell[1];
                // Roll the ball in the same direction until it hits
                // the wall
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += direction[0];
                    y += direction[1];
                }
                // Rollback the last move
                x -= direction[0];
                y -= direction[1];
                if (!visited[x][y]) {
                    cells.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final TheMaze theMaze = new TheMaze();

        int[][] maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        System.out.println(theMaze.hasPath(maze, start, destination));

        maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        start = new int[]{0, 4};
        destination = new int[]{3, 2};
        System.out.println(theMaze.hasPath(maze, start, destination));
    }
}
