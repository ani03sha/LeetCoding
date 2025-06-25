package org.redquark.leetcoding.backtracking;

public class NQueensII {

    private int queenCount = 0;

    public int totalNQueens(int n) {
        // List to store the positions of queens on the board
        if (n <= 0) {
            return 0;
        }
        // Board to place queens
        final int[][] board = new int[n][n];
        // Perform backtracking
        backtrack(board, 0);
        return queenCount;
    }

    private void backtrack(int[][] board, int column) {
        if (column == board.length) {
            queenCount++;
            return;
        }
        // Traverse for all the rows
        for (int row = 0; row < board.length; row++) {
            // Check if we can place the queen
            if (canPlace(board, row, column)) {
                board[row][column] = 1;
                backtrack(board, column + 1);
                board[row][column] = 0;
            }
        }
    }

    private boolean canPlace(int[][] board, int row, int column) {
        for (int i = 0; i < column; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = column; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = column; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Optimized solution using boolean arrays to track occupied columns,
     */

    private int count = 0;

    public int totalNQueensOptimized(int n) {
        // Arrays to track if a column, diagonal, and
        // anti-diagonal are occupied
        final boolean[] columns = new boolean[n];
        final boolean[] diagonals = new boolean[2 * n - 1];
        final boolean[] antiDiagonals = new boolean[2 * n - 1];
        // Perform backtracking on all positions
        backtrack(0, n, columns, diagonals, antiDiagonals);
        return count;
    }

    private void backtrack(int row, int n, boolean[] columns, boolean[] diagonals, boolean[] antiDiagonals) {
        // If all the rows have been evaluated,
        // it means one solution is found
        if (row == n) {
            count++;
            return;
        }
        // Check for all columns
        for (int column = 0; column < n; column++) {
            // Indices of diagonal and antiDiagonal
            final int diagonalIndex = row + column;
            final int antiDiagonalIndex = row - column + (n - 1);
            // Check if a queen already exists in attacking positions
            if (columns[column] || diagonals[diagonalIndex] || antiDiagonals[antiDiagonalIndex]) {
                continue;
            }
            // Mark queen at the current position
            columns[column] = true;
            diagonals[diagonalIndex] = true;
            antiDiagonals[antiDiagonalIndex] = true;
            // Check for next row
            backtrack(row + 1, n, columns, diagonals, antiDiagonals);
            // Unmark the queen from the current position
            columns[column] = false;
            diagonals[diagonalIndex] = false;
            antiDiagonals[antiDiagonalIndex] = false;
        }
    }

    public static void main(String[] args) {
        final NQueensII nQueensII = new NQueensII();

        System.out.println(nQueensII.totalNQueens(4));
        System.out.println(nQueensII.totalNQueensOptimized(4));
        nQueensII.queenCount = 0;
        nQueensII.count = 0;
        System.out.println(nQueensII.totalNQueens(1));
        System.out.println(nQueensII.totalNQueensOptimized(1));
    }
}
