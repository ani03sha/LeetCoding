package org.redquark.leetcoding.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // List to store the final output
        final List<List<Integer>> cells = new ArrayList<>();
        // Special case
        if (heights == null || heights.length == 0) {
            return cells;
        }
        // Dimensions of the grid
        final int m = heights.length;
        final int n = heights[0].length;
        // Queues to perform BFS for both oceans
        final Deque<int[]> pacificCells = new ArrayDeque<>();
        final Deque<int[]> atlanticCells = new ArrayDeque<>();
        // Cells to mark visited cells
        final boolean[][] pacificVisited = new boolean[m][n];
        final boolean[][] atlanticVisited = new boolean[m][n];
        // Process the boundaries first
        for (int i = 0; i < m; i++) {
            pacificCells.offer(new int[]{i, 0});
            pacificVisited[i][0] = true;
            atlanticCells.offer(new int[]{i, n - 1});
            atlanticVisited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pacificCells.offer(new int[]{0, j});
            pacificVisited[0][j] = true;
            atlanticCells.offer(new int[]{m - 1, j});
            atlanticVisited[m - 1][j] = true;
        }
        // Perform BFS on both the queues
        bfs(heights, pacificCells, pacificVisited, m, n);
        bfs(heights, atlanticCells, atlanticVisited, m, n);
        // Process all cells once again
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    cells.add(List.of(i, j));
                }
            }
        }
        return cells;
    }


    private void bfs(int[][] heights, Deque<int[]> cells, boolean[][] visited, int m, int n) {
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!cells.isEmpty()) {
            final int size = cells.size();
            for (int i = 0; i < size; i++) {
                final int[] cell = cells.remove();
                // Check in four directions
                for (int[] direction : directions) {
                    final int x = cell[0] + direction[0];
                    final int y = cell[1] + direction[1];
                    // Check for valid cells
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && heights[x][y] >= heights[cell[0]][cell[1]]) {
                        visited[x][y] = true;
                        cells.offer(new int[]{x, y});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();

        int[][] heights = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        System.out.println(pacificAtlanticWaterFlow.pacificAtlantic(heights));

        heights = new int[][]{{1}};
        System.out.println(pacificAtlanticWaterFlow.pacificAtlantic(heights));
    }
}
