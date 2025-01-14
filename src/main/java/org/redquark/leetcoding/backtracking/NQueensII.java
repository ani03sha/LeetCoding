package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueensII {

    private int queenCount = 0;

    public int totalNQueens(int n) {
        // List to store the positions of queens on the board
        final List<List<String>> positions = new ArrayList<>();
        if (n <= 0) {
            return 0;
        }
        // Board to place queens
        final int[][] board = new int[n][n];
        // Perform backtracking
        backtrack(board, 0, positions);
        return queenCount;
    }

    private void backtrack(int[][] board, int column, List<List<String>> positions) {
        if (column == board.length) {
            queenCount++;
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

    public static void main(String[] args) {
        final NQueensII nQueensII = new NQueensII();

        System.out.println(nQueensII.totalNQueens(4));
        nQueensII.queenCount = 0;
        System.out.println(nQueensII.totalNQueens(1));
    }
}
