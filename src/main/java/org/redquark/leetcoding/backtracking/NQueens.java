package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        final NQueens nQueens = new NQueens();

        System.out.println(nQueens.solveNQueens(4));
        System.out.println(nQueens.solveNQueens(1));
    }
}
