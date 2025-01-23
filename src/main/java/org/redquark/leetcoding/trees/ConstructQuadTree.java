package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.QuadTreeNode;

public class ConstructQuadTree {

    public QuadTreeNode construct(int[][] grid) {
        // Special case
        if (grid == null || grid.length == 0) {
            return null;
        }
        // Order of the matrix
        int n = grid.length;
        // Perform DFS on the matrix
        return dfs(grid, n, 0, 0);
    }

    private QuadTreeNode dfs(int[][] grid, int n, int row, int column) {
        // Check if all the values in the grid are same
        boolean areAllValuesSame = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[row][column] != grid[row + i][column + j]) {
                    areAllValuesSame = false;
                    break;
                }
            }
        }
        // If all values are same, it means current value is a leaf QuadTreeNode
        if (areAllValuesSame) {
            return new QuadTreeNode(grid[row][column] == 1, true);
        }
        // If grid have different values, then we will recurse on subgrid
        n /= 2;
        // Create all four QuadTreeNodes
        QuadTreeNode topLeft = dfs(grid, n, row, column);
        QuadTreeNode topRight = dfs(grid, n, row, column + n);
        QuadTreeNode bottomLeft = dfs(grid, n, row + n, column);
        QuadTreeNode bottomRight = dfs(grid, n, row + n, column + n);
        // Create an intermediate QuadTreeNode
        return new QuadTreeNode(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    public static void main(String[] args) {
        final ConstructQuadTree constructQuadTree = new ConstructQuadTree();

        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        QuadTreeNode root = constructQuadTree.construct(grid);
        root.printQuadTree(root, "");

        grid = new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
        };
        root = constructQuadTree.construct(grid);
        root.printQuadTree(root, "  ");
    }
}
