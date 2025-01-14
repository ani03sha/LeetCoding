package org.redquark.leetcoding.dfs;

import java.util.Arrays;

public class SurroundedRegions {

    public void solve(char[][] board) {
        // Special case
        if (board == null || board.length == 0) {
            return;
        }
        // Dimensions of the board
        final int m = board.length;
        final int n = board[0].length;
        // Capture boundary cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    boundaryDFS(board, i, j, m, n);
                }
            }
        }
        // Replace all connected O's to X's
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        // Revert all the boundary cells to O's
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void boundaryDFS(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        boundaryDFS(board, i + 1, j, m, n);
        boundaryDFS(board, i - 1, j, m, n);
        boundaryDFS(board, i, j + 1, m, n);
        boundaryDFS(board, i, j - 1, m, n);
    }

    public static void main(String[] args) {
        final SurroundedRegions surroundedRegions = new SurroundedRegions();

        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}
        };
        surroundedRegions.solve(board);
        System.out.println(Arrays.deepToString(board));

        board = new char[][]{{'X'}};
        surroundedRegions.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
