package org.redquark.leetcoding.binarysearch;

import java.util.LinkedList;
import java.util.Queue;

public class LastDayWhereYouCanStillCross {

    public int latestDayToCrossBFS(int row, int column, int[][] cells) {
        // Last day when we can cross
        int lastDay = 0;
        // Four directions
        final int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        // We need to perform binary search to identify the
        // last day on which we can still cross with the
        // following rules -
        // 1. If we can cross on day i, we can cross any day before it
        // 2. If you can't cross on day i, we can't cross on day after it
        // Since every day one cell is flooded, so our search space is
        // from day 1 to day row * column
        int left = 1;
        int right = row * column;
        // Perform binary search on this space
        while (left <= right) {
            // Let's try middle-th day
            final int middle = left + (right - left) / 2;
            // Perform BFS on this day
            if (canCrossBFS(row, column, middle, cells, directions)) {
                lastDay = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastDay;
    }

    private boolean canCrossBFS(int row, int column, int currentDay, int[][] cells, int[][] directions) {
        // Create a grid of land
        final int[][] grid = new int[row][column];
        // Flood this grid until the currentDay
        for (int i = 0; i < currentDay; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        // Queue to perform BFS
        final Queue<int[]> queue = new LinkedList<>();
        // Add all land cells from the first row to the queue
        for (int i = 0; i < column; i++) {
            if (grid[0][i] == 0) {
                queue.offer(new int[]{0, i});
                grid[0][i] = -1; // Mark as visited
            }
        }
        // Perform BFS here
        while (!queue.isEmpty()) {
            // Get current cell
            final int[] current = queue.remove();
            // If we have reached the last row
            if (current[0] == row - 1) {
                return true;
            }
            // Traverse in all four directions
            for (int[] direction : directions) {
                final int x = current[0] + direction[0];
                final int y = current[1] + direction[1];
                // Check of cell validity
                if (x < 0 || x >= row || y < 0 || y >= column || grid[x][y] != 0) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                // Mark the cell visited
                grid[x][y] = -1;
            }
        }
        return false;
    }

    public int latestDayToCrossDFS(int row, int column, int[][] cells) {
        // Last day when we can cross
        int lastDay = 0;
        // We need to perform binary search to identify the
        // last day on which we can still cross with the
        // following rules -
        // 1. If we can cross on day i, we can cross any day before it
        // 2. If you can't cross on day i, we can't cross on day after it
        // Since every day one cell is flooded, so our search space is
        // from day 1 to day row * column
        int left = 1;
        int right = row * column;
        // Perform binary search on this space
        while (left <= right) {
            // Let's try middle-th day
            final int middle = left + (right - left) / 2;
            // Perform BFS on this day
            if (canCrossDFS(row, column, middle, cells)) {
                lastDay = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastDay;
    }

    private boolean canCrossDFS(int row, int column, int day, int[][] cells) {
        // Create a grid
        final int[][] grid = new int[row][column];
        // Set all cells until day to 1
        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        // Check for all cells in the first row
        for (int i = 0; i < column; i++) {
            if (grid[0][i] == 0 && dfs(grid, 0, i, row, column)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int[][] grid, int i, int j, int row, int column) {
        // Base case
        if (i < 0 || i >= row || j < 0 || j >= column || grid[i][j] != 0) {
            return false;
        }
        // If we have reached the last row
        if (i == row - 1) {
            return true;
        }
        // Mark visited
        grid[i][j] = -1;
        // Traverse in four directions
        return dfs(grid, i - 1, j, row, column)
                || dfs(grid, i + 1, j, row, column)
                || dfs(grid, i, j - 1, row, column)
                || dfs(grid, i, j + 1, row, column);
    }

    public int latestDayToCrossUnionFind(int row, int column, int[][] cells) {
        final UnionFind unionFind = new UnionFind(row * column + 2);
        // Create a new grid
        final int[][] grid = new int[row][column];
        // Four directions
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Traverse for all given cells in reverse order because
        // the last day when there's a path is the first day in the
        // reverse order
        for (int i = cells.length - 1; i >= 0; i--) {
            final int r = cells[i][0] - 1;
            final int c = cells[i][1] - 1;
            // Mark the cell as blocked (flooded)
            grid[r][c] = 1;
            // Flattened index for the current cell
            final int index1 = r * column + c + 1;
            // Check in all four directions
            for (int[] direction : directions) {
                final int x = r + direction[0];
                final int y = c + direction[1];
                // Check for cell validity
                if (x < 0 || x >= row || y < 0 || y >= column || grid[x][y] != 1) {
                    continue;
                }
                // Flattened index for the adjacent cell
                final int index2 = x * column + y + 1;
                unionFind.union(index1, index2);
            }
            if (r == 0) {
                // Connect the first row to the virtual top cell
                unionFind.union(0, index1);
            }
            if (r == row - 1) {
                // Connect the last row to the virtual bottom cell
                unionFind.union(row * column + 1, index1);
            }
            // Check if the virtual top and bottom cells are connected
            if (unionFind.find(0) == unionFind.find(row * column + 1)) {
                return i; // Return the last day when we can cross
            }
        }
        return -1;
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] ranks;

        public UnionFind(int size) {
            this.parents = new int[size];
            this.ranks = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public int find(int param) {
            if (parents[param] != param) {
                parents[param] = find(parents[param]);
            }
            return parents[param];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (this.ranks[rootX] <= this.ranks[rootY]) {
                this.parents[rootX] = rootY;
                this.ranks[rootY] += this.ranks[rootX];
            } else {
                this.parents[rootY] = rootX;
                this.ranks[rootX] += this.ranks[rootY];
            }
        }
    }

    public static void main(String[] args) {
        final LastDayWhereYouCanStillCross lastDayWhereYouCanStillCross = new LastDayWhereYouCanStillCross();

        int row = 2;
        int col = 2;
        int[][] cells = {{1, 1}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println("Latest day to cross using BFS: " + lastDayWhereYouCanStillCross.latestDayToCrossBFS(row, col, cells));
        System.out.println("Latest day to cross using DFS: " + lastDayWhereYouCanStillCross.latestDayToCrossDFS(row, col, cells));
        System.out.println("Latest day to cross using Union-Find: " + lastDayWhereYouCanStillCross.latestDayToCrossUnionFind(row, col, cells));

        row = 3;
        col = 3;
        cells = new int[][]{{1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 2}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};
        System.out.println("Latest day to cross using BFS: " + lastDayWhereYouCanStillCross.latestDayToCrossBFS(row, col, cells));
        System.out.println("Latest day to cross using DFS: " + lastDayWhereYouCanStillCross.latestDayToCrossDFS(row, col, cells));
        System.out.println("Latest day to cross using Union-Find: " + lastDayWhereYouCanStillCross.latestDayToCrossUnionFind(row, col, cells));
    }
}
