package org.redquark.leetcoding.design;

public class DesignTicTacToe {

    static class TicTacToe {

        // Rows and columns arrays
        private final int[] rows;
        private final int[] columns;
        // Variables to keep track of diagonal and anti-diagonal
        private int diagonal;
        private int antiDiagonal;
        // Order of the board;
        private final int n;

        public TicTacToe(int n) {
            this.n = n;
            this.rows = new int[n];
            this.columns = new int[n];
        }

        public int move(int row, int col, int player) {
            final int currentPlayer = (player == 1) ? 1 : -1;
            // Update current player in current row and column
            this.rows[row] += currentPlayer;
            this.columns[col] += currentPlayer;
            // Update diagonals
            if (row == col) {
                this.diagonal += currentPlayer;
            }
            // Update anti-diagonal
            if (col == n - row - 1) {
                this.antiDiagonal += currentPlayer;
            }
            // Check if current player wins
            if (Math.abs(this.rows[row]) == n || Math.abs(this.columns[col]) == n || Math.abs(this.diagonal) == n || Math.abs(this.antiDiagonal) == n) {
                return player;
            }
            // No one wins
            return 0;
        }
    }

    public static void main(String[] args) {
        final TicTacToe ticTacToe = new TicTacToe(3);
        System.out.println(ticTacToe.move(0, 0, 1));
        System.out.println(ticTacToe.move(0, 2, 2));
        System.out.println(ticTacToe.move(2, 2, 1));
        System.out.println(ticTacToe.move(1, 1, 2));
        System.out.println(ticTacToe.move(2, 0, 1));
        System.out.println(ticTacToe.move(1, 0, 2));
        System.out.println(ticTacToe.move(2, 1, 1));
    }
}
