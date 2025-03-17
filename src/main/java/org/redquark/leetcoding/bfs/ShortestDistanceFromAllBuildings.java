package org.redquark.leetcoding.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {

    public int shortestDistance(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return -1;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Queue to perform BFS
        final Queue<int[]> cells = new LinkedList<>();
        // Count of buildings in the grid
        int buildingCount = 0;
        // Count of buildings each cell can reach
        final int[][] counts = new int[m][n];
        // Distances from each cell to all buildings
        final int[][] distances = new int[m][n];
        // Four directions
        final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // Process each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Perform BFS only from the buildings
                if (grid[i][j] == 1) {
                    buildingCount++;
                    cells.offer(new int[]{i, j});
                    // Depth of BFS - distance from the start
                    int depth = 0;
                    // Visited cells
                    final boolean[][] visited = new boolean[m][n];
                    while (!cells.isEmpty()) {
                        depth++;
                        final int size = cells.size();
                        for (int k = 0; k < size; k++) {
                            // Current cell
                            final int[] cell = cells.remove();
                            // Traverse in four directions
                            for (int[] direction : directions) {
                                final int x = cell[0] + direction[0];
                                final int y = cell[1] + direction[1];
                                // Explore the next valid cell
                                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y]) {
                                    counts[x][y]++;
                                    distances[x][y] += depth;
                                    cells.offer(new int[]{x, y});
                                    visited[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Minimum distance
        int minimumDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && counts[i][j] == buildingCount) {
                    minimumDistance = Math.min(minimumDistance, distances[i][j]);
                }
            }
        }
        return minimumDistance == Integer.MAX_VALUE ? -1 : minimumDistance;
    }

    public static void main(String[] args) {
        final ShortestDistanceFromAllBuildings shortestDistanceFromAllBuildings = new ShortestDistanceFromAllBuildings();

        int[][] grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance(grid));

        grid = new int[][]{{1, 0}};
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance(grid));

        grid = new int[][]{{1}};
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance(grid));
    }
}
