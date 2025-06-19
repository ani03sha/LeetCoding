package org.redquark.leetcoding.arrays;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        // Order of the board
        final int n = board.length;
        // Boolean arrays to mark existing numbers in the board
        final boolean[][] rows = new boolean[n][n];
        final boolean[][] columns = new boolean[n][n];
        final boolean[][] boxes = new boolean[n][n];
        // Process every cell on the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Skip empty cell
                if (board[i][j] == '.') {
                    continue;
                }
                final int number = board[i][j] - '0' - 1;
                // Index for box
                final int boxIndex = 3 * (i / 3) + j / 3;
                // Check if the number has already been encountered
                if (rows[i][number] || columns[j][number] || boxes[boxIndex][number]) {
                    return false;
                }
                rows[i][number] = true;
                columns[j][number] = true;
                boxes[boxIndex][number] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final ValidSudoku validSudoku = new ValidSudoku();

        char[][] board = {
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
        System.out.println(validSudoku.isValidSudoku(board)); // true

        board = new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(validSudoku.isValidSudoku(board)); // false
    }
}
