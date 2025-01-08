package org.redquark.leetcoding.graphs;

public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Perimeter of the grid
        int perimeter = 0;
        // Process every cell and then perform DFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        final IslandPerimeter islandPerimeter = new IslandPerimeter();

        int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(islandPerimeter.islandPerimeter(grid));

        grid = new int[][]{{1}};
        System.out.println(islandPerimeter.islandPerimeter(grid));

        grid = new int[][]{{1, 0}};
        System.out.println(islandPerimeter.islandPerimeter(grid));
    }
}
