package org.redquark.leetcoding.dynamicprogramming;

public class KnightProbabilityInChessboard {

    public double knightProbability(int n, int k, int row, int column) {
        // Eight directions
        final int[][] directions = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
        // Lookup table to store probability of being inside table after k moves
        final double[][][] lookup = new double[n][n][k + 1];
        // Recursively find the solution
        return dfs(n, k, row, column, directions, lookup);
    }

    private double dfs(int n, int k, int row, int column, int[][] directions, double[][][] lookup) {
        // Check for valid cells
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        // If we have moved k times
        if (k == 0) {
            return 1;
        }
        // Check if we have already tried this cell
        if (lookup[row][column][k] != 0) {
            return lookup[row][column][k];
        }
        // Probability for this move
        double probability = 0;
        for (int[] direction : directions) {
            probability += 0.125 * dfs(n, k - 1, row + direction[0], column + direction[1], directions, lookup);
        }
        return lookup[row][column][k] = probability;
    }

    public static void main(String[] args) {
        final KnightProbabilityInChessboard knightProbabilityInChessboard = new KnightProbabilityInChessboard();

        int n = 3;
        int k = 2;
        int row = 0;
        int column = 0;
        System.out.println(knightProbabilityInChessboard.knightProbability(n, k, row, column));

        n = 1;
        k = 0;
        row = 0;
        column = 0;
        System.out.println(knightProbabilityInChessboard.knightProbability(n, k, row, column));
    }
}
