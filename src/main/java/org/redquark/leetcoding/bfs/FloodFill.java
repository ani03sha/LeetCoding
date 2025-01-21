package org.redquark.leetcoding.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // Special case
        if (image == null || image.length == 0 || image[sr][sc] == color) {
            return image;
        }
        // Dimensions of the image
        final int m = image.length;
        final int n = image[0].length;
        // Queue to perform BFS
        final Queue<int[]> cells = new LinkedList<>();
        // Add source cell to the queue
        cells.offer(new int[]{sr, sc});
        int previousColor = image[sr][sc];
        image[sr][sc] = color;
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Perform BFS
        while (!cells.isEmpty()) {
            // Get the current cell
            int[] cell = cells.remove();
            // Traverse in all four directions
            for (int[] direction : directions) {
                final int x = cell[0] + direction[0];
                final int y = cell[1] + direction[1];
                // Check if x, y are valid cells and they are not visited yet
                if (x < 0 || x >= m || y < 0 || y >= n || image[x][y] != previousColor) {
                    continue;
                }
                image[x][y] = color;
                cells.offer(new int[]{x, y});
            }
        }
        return image;
    }

    public static void main(String[] args) {
        final FloodFill floodFill = new FloodFill();

        int[][] image = new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int sr = 1;
        int sc = 1;
        int color = 2;
        System.out.println(Arrays.deepToString(floodFill.floodFill(image, sr, sc, color)));

        image = new int[][]{
                {0, 0, 0},
                {0, 0, 0}
        };
        sr = 0;
        sc = 0;
        color = 0;
        System.out.println(Arrays.deepToString(floodFill.floodFill(image, sr, sc, color)));

    }
}
