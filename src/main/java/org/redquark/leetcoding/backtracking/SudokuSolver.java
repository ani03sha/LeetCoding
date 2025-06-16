package org.redquark.leetcoding.backtracking;

import java.util.Arrays;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        // Special case
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        // Perform backtracking
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        // Traverse for all the cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Check if the current cell is empty
                if (board[i][j] == '.') {
                    // Try every character from 1 to 9
                    for (char c = '1'; c <= '9'; c++) {
                        // Check if the current character placement is valid
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            // If we have found the solution, we will return true
                            if (backtrack(board)) {
                                return true;
                            }
                            // Otherwise go back
                            else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int column, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][column] != '.' && board[i][column] == c) {
                return false;
            }
            if (board[row][i] != '.' && board[row][i] == c) {
                return false;
            }
            if (board[3 * (row / 3) + i / 3][3 * (column / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (column / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final SudokuSolver sudokuSolver = new SudokuSolver();

        final char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        sudokuSolver.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
