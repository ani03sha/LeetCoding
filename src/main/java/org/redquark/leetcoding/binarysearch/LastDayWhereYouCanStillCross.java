package org.redquark.leetcoding.binarysearch;

import java.util.LinkedList;
import java.util.Queue;

public class LastDayWhereYouCanStillCross {

    public int latestDayToCrossBFS(int row, int col, int[][] cells) {
        // Last day when we can cross
        int lastDay = 0;
        // Four directions
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Left and right pointers for binary search
        int left = 1;
        int right = row * col;
        // Perform binary search in this space
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            // Check if we can cross on "middle"-th day
            if (canCrossBFS(row, col, cells, middle, directions)) {
                lastDay = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastDay;
    }

    private boolean canCrossBFS(int row, int col, int[][] cells, int day, int[][] directions) {
        // Create a grid
        final int[][] grid = new int[row][col];
        // Queue for BFS
        final Queue<int[]> queue = new LinkedList<>();
        // Set all cells until day to 1
        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        // Add all cells in the first row to queue
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                queue.offer(new int[]{0, i});
                grid[0][i] = -1; // Mark as visited
            }
        }
        // Perform BFS
        while (!queue.isEmpty()) {
            final int[] current = queue.remove();
            // If we have reached the last row
            if (current[0] == row - 1) {
                return true;
            }
            // Traverse in four directions
            for (int[] direction : directions) {
                final int x = current[0] + direction[0];
                final int y = current[1] + direction[1];
                // Check for cell validity
                if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] != 0) {
                    continue;
                }
                grid[x][y] = -1;
                queue.offer(new int[]{x, y});
            }
        }
        return false;
    }

    public int latestDayToCrossDFS(int row, int col, int[][] cells) {
        // Last day when we can cross
        int lastDay = 0;
        // Left and right pointers for binary search
        int left = 1;
        int right = row * col;
        // Perform binary search in this space
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            // Check if we can cross on "middle"-th day
            if (canCrossDFS(row, col, cells, middle)) {
                lastDay = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastDay;
    }

    private boolean canCrossDFS(int row, int col, int[][] cells, int day) {
        // Create a grid
        final int[][] grid = new int[row][col];
        // Set all cells until day to 1
        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        // Check for all cells in the first row
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0 && dfs(grid, 0, i, row, col)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int[][] grid, int i, int j, int row, int col) {
        // Base case
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != 0) {
            return false;
        }
        // If we have reached the last row
        if (i == row - 1) {
            return true;
        }
        // Mark visited
        grid[i][j] = -1;
        // Traverse in four directions
        return dfs(grid, i - 1, j, row, col)
                || dfs(grid, i + 1, j, row, col)
                || dfs(grid, i, j - 1, row, col)
                || dfs(grid, i, j + 1, row, col);
    }

    public int latestDayToCrossUnionFind(int row, int col, int[][] cells) {
        final UnionFind unionFind = new UnionFind(row * col + 2);
        final int[][] grid = new int[row][col];
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Traverse for all given cells
        for (int i = cells.length - 1; i >= 0; i--) {
            final int r = cells[i][0] - 1;
            final int c = cells[i][1] - 1;
            // Mark the cell as blocked
            grid[r][c] = 1;
            // Flattened index for the current cell
            final int index1 = r * col + c + 1;
            // Check in all four directions
            for (int[] direction : directions) {
                final int x = r + direction[0];
                final int y = c + direction[1];
                // Check for cell validity
                if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] != 1) {
                    continue;
                }
                // Flattened index for the adjacent cell
                final int index2 = x * col + y + 1;
                unionFind.union(index1, index2);
            }
            if (r == 0) {
                // Connect the first row to the virtual top cell
                unionFind.union(0, index1);
            }
            if (r == row - 1) {
                // Connect the last row to the virtual bottom cell
                unionFind.union(row * col + 1, index1);
            }
            // Check if the virtual top and bottom cells are connected
            if (unionFind.find(0) == unionFind.find(row * col + 1)) {
                return i; // Return the last day when we can cross
            }
        }
        return -1;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int param) {
            if (parent[param] != param) {
                parent[param] = find(parent[param]);
            }
            return parent[param];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (this.rank[rootX] <= this.rank[rootY]) {
                this.parent[rootX] = rootY;
                this.rank[rootY] += this.rank[rootX];
            } else {
                this.parent[rootY] = rootX;
                this.rank[rootX] += this.rank[rootY];
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
