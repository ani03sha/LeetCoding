package org.redquark.leetcoding.bfs;

import java.util.Arrays;

public class GameOfLife {

    public void gameOfLife(int[][] board) {
        // Special case
        if (board == null || board.length == 0) {
            return;
        }
        // Dimensions of the board
        final int m = board.length;
        final int n = board[0].length;
        // Eight directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
        // Process each cell on the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Count of live neighbors
                int liveNeighbors = 0;
                // Check in all 8 directions
                for (int[] direction : directions) {
                    final int x = i + direction[0];
                    final int y = j + direction[1];
                    // Check for valid cells
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (board[x][y] == 1 || board[x][y] == 2) {
                        liveNeighbors++;
                    }
                }
                // Check for rules
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = 2;
                }
            }
        }
        // Restore the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] %= 2;
            }
        }
    }

    public static void main(String[] args) {
        final GameOfLife gameOfLife = new GameOfLife();

        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));

        board = new int[][]{{1, 1}, {1, 0}};
        gameOfLife.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
