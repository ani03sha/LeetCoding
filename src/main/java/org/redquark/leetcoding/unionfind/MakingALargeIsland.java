package org.redquark.leetcoding.unionfind;

import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {

    public int largestIsland(int[][] grid) {
        // Dimensions of the grid
        final int m = grid.length;
        final int n = grid[0].length;
        // Create an instance of UnionFind
        final UnionFind unionFind = new UnionFind(m * n);
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // Process every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell is an island
                if (grid[i][j] == 1) {
                    // Get ID of the current node
                    final int id1 = i * n + j;
                    // Traverse in four directions of this cell
                    for (int[] direction : directions) {
                        final int x = direction[0] + i;
                        final int y = direction[1] + j;
                        // Check for the validity of the cell
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                            continue;
                        }
                        // Get ID for the neighboring node
                        final int id2 = x * n + y;
                        // Union these two cells
                        unionFind.union(id1, id2);
                    }
                }
            }
        }
        // Calculate the island with maximum area
        int maxIslandArea = 0;
        // Check if there are any zeroes in the grid
        boolean hasZero = false;
        // Set to store unique roots of 0's neighbors
        final Set<Integer> uniques = new HashSet<>();
        // Process the grid again
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell is 0
                if (grid[i][j] == 0) {
                    hasZero = true;
                    // Flip this zero
                    int currentIslandArea = 1;
                    // Traverse in four directions
                    for (int[] direction : directions) {
                        final int x = direction[0] + i;
                        final int y = direction[1] + j;
                        // Check of the bounds
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                            continue;
                        }
                        // Get id
                        final int id = x * n + y;
                        // Get the root of this cell
                        final int root = unionFind.find(id);
                        // Add this root to the set
                        uniques.add(root);
                    }
                    // Sum the sizes of unique neighboring islands
                    for (int root : uniques) {
                        currentIslandArea += unionFind.ranks[root];
                    }
                    // Clear the set for next 0
                    uniques.clear();
                    // Update maxArea if required
                    maxIslandArea = Math.max(maxIslandArea, currentIslandArea);
                }
            }
        }
        // If there are no zeroes in the island
        if (!hasZero) {
            return m * n;
        }
        return maxIslandArea;
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] ranks;

        UnionFind(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
                this.ranks[i] = 1;
            }
        }

        void union(int a, int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (this.ranks[rootB] < this.ranks[rootA]) {
                this.parents[rootB] = rootA;
                this.ranks[rootA] += this.ranks[rootB];
            } else {
                this.parents[rootA] = rootB;
                this.ranks[rootB] += this.ranks[rootA];
            }
        }

        int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = find(this.parents[a]);
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final MakingALargeIsland makingALargeIsland = new MakingALargeIsland();

        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println(makingALargeIsland.largestIsland(grid));

        grid = new int[][]{{1, 1}, {1, 0}};
        System.out.println(makingALargeIsland.largestIsland(grid));

        grid = new int[][]{{1, 1}, {1, 1}};
        System.out.println(makingALargeIsland.largestIsland(grid));
    }
}
