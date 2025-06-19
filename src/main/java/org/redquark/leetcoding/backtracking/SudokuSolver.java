package org.redquark.leetcoding.backtracking;

import java.util.Arrays;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        // Special case
        if (board == null || board.length == 0) {
            return;
        }
        // Perform backtracking on the board
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        // Traverse for all the cells
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                // Only process if it is an empty cell
                if (board[row][column] == '.') {
                    // Try every number from 1 to 9
                    for (char current = '1'; current <= '9'; current++) {
                        // Check if the board will remain valid if we insert
                        // the current character at position (row, column)
                        if (isValid(board, row, column, current)) {
                            board[row][column] = current;
                            // If we have found the solution, return true
                            if (backtrack(board)) {
                                return true;
                            } else {
                                // Else, we go back
                                board[row][column] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int column, char current) {
        for (int i = 0; i < 9; i++) {
            // Check for rows
            if (board[i][column] != '.' && board[i][column] == current) {
                return false;
            }
            // Check for columns
            if (board[row][i] != '.' && board[row][i] == current) {
                return false;
            }
            // Check for boxes
            if (board[3 * (row / 3) + i / 3][3 * (column / 3) + i % 3] != '.' && board[3 * (row / 3) + i / 3][3 * (column / 3) + i % 3] == current) {
                return false;
            }
        }
        return true;
    }

    // Boolean arrays to mark used numbers in rows, columns, and boxes
    final boolean[][] rows = new boolean[9][9];
    final boolean[][] columns = new boolean[9][9];
    final boolean[][] boxes = new boolean[9][9];

    public void solveSudokuOptimized(char[][] board) {
        // Mark all numbers that are used in the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    final int number = board[i][j] - '1';
                    final int boxIndex = getBoxIndex(i, j);
                    this.rows[i][number] = true;
                    this.columns[j][number] = true;
                    this.boxes[boxIndex][number] = true;
                }
            }
        }
        // Perform backtracking from the initial point
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int column) {
        // If the current row is completed, move to the next row
        if (column == 9) {
            row++;
            column = 0;
        }
        // If all the rows are completed
        if (row == 9) {
            return true;
        }
        // If the current cell is already filled, move to the next cell
        if (board[row][column] != '.') {
            return backtrack(board, row, column + 1);
        }
        // Now, try placing numbers from 1 to 9
        for (int number = 0; number < 9; number++) {
            // Get box index
            final int boxIndex = getBoxIndex(row, column);
            // Check if it is valid to put the current character in the cell
            if (isValid(row, column, boxIndex, number)) {
                board[row][column] = (char) (number + '1');
                mark(row, column, boxIndex, number, true);
                // Move to the next cell
                if (backtrack(board, row, column + 1)) {
                    return true;
                }
                // Backtrack
                board[row][column] = '.';
                mark(row, column, boxIndex, number, false);
            }
        }
        return false;
    }

    private int getBoxIndex(int row, int column) {
        return 3 * (row / 3) + column / 3;
    }

    private boolean isValid(int row, int column, int boxIndex, int number) {
        return !this.rows[row][number] && !this.columns[column][number] && !this.boxes[boxIndex][number];
    }

    private void mark(int row, int column, int boxIndex, int number, boolean flag) {
        this.rows[row][number] = flag;
        this.columns[column][number] = flag;
        this.boxes[boxIndex][number] = flag;
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
        final char[][] boardOptimized = new char[][]{
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
        sudokuSolver.solveSudokuOptimized(boardOptimized);
        System.out.println(Arrays.deepToString(boardOptimized));
    }
}
