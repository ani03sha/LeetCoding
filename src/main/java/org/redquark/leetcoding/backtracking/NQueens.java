package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        // List to store the positions of queens on the board
        final List<List<String>> positions = new ArrayList<>();
        if (n <= 0) {
            return positions;
        }
        // Board to place queens
        final int[][] board = new int[n][n];
        // Perform backtracking
        backtrack(board, 0, positions);
        return positions;
    }

    private void backtrack(int[][] board, int column, List<List<String>> positions) {
        if (column == board.length) {
            positions.add(addPosition(board));
            return;
        }
        // Traverse for all the rows
        for (int row = 0; row < board.length; row++) {
            // Check if we can place the queen
            if (canPlace(board, row, column)) {
                board[row][column] = 1;
                backtrack(board, column + 1, positions);
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

    private List<String> addPosition(int[][] board) {
        final List<String> position = new ArrayList<>();
        for (int[] b : board) {
            final StringBuilder s = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                if (b[i] == 1) {
                    s.append("Q");
                } else {
                    s.append(".");
                }
            }
            position.add(s.toString());
        }
        return position;
    }

    /**
     * Optimized solution for the N-Queens problem
     */

    public List<List<String>> solveNQueensOptimized(int n) {
        // List to store all valid results
        final List<List<String>> positions = new ArrayList<>();
        // Arrays to mark positions of queens in columns, diagonals
        // and anti-diagonals
        final boolean[] columns = new boolean[n];
        final boolean[] diagonals = new boolean[2 * n - 1];
        final boolean[] antiDiagonals = new boolean[2 * n - 1];
        // Position of queens
        final int[] queens = new int[n];
        // Perform backtracking
        backtrack(0, n, queens, columns, diagonals, antiDiagonals, positions);
        return positions;
    }

    private void backtrack(int row, int n, int[] queens, boolean[] columns, boolean[] diagonals, boolean[] antiDiagonals, List<List<String>> positions) {
        // Base case
        if (row == n) {
            positions.add(buildBoard(queens, n));
            return;
        }
        for (int column = 0; column < n; column++) {
            final int diagonalIndex = row + column;
            final int antiDiagonalIndex = row - column + (n - 1);
            // If the current column doesn't yield the result, we return
            if (columns[column] || diagonals[diagonalIndex] || antiDiagonals[antiDiagonalIndex]) {
                continue;
            }
            // Place the queen in the current row
            queens[row] = column;
            // Mark corresponding cells as vulnerable to attack
            columns[column] = true;
            diagonals[diagonalIndex] = true;
            antiDiagonals[antiDiagonalIndex] = true;
            // Backtrack on next row
            backtrack(row + 1, n, queens, columns, diagonals, antiDiagonals, positions);
            // Unmark corresponding cells as vulnerable to attack
            columns[column] = false;
            diagonals[diagonalIndex] = false;
            antiDiagonals[antiDiagonalIndex] = false;
        }
    }

    private List<String> buildBoard(int[] queens, int n) {
        final List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            final char[] rowArray = new char[n];
            Arrays.fill(rowArray, '.');
            rowArray[queens[row]] = 'Q';
            board.add(new String(rowArray));
        }
        return board;
    }

    public static void main(String[] args) {
        final NQueens nQueens = new NQueens();

        System.out.println(nQueens.solveNQueens(4));
        System.out.println(nQueens.solveNQueens(1));
        System.out.println(nQueens.solveNQueensOptimized(4));
        System.out.println(nQueens.solveNQueensOptimized(1));
    }
}
