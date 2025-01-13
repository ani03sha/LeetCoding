package org.redquark.leetcoding.backtracking;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        // Special case
        if (board == null || board.length == 0) {
            return false;
        }
        // Dimensions of the grid
        final int m = board.length;
        final int n = board[0].length;
        // Array to keep track of visited cells
        final boolean[][] visited = new boolean[m][n];
        // Process every cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, m, n, visited, 0, word)) {
                    // Perform DFS from this cell
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int m, int n, boolean[][] visited, int index, String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || word.charAt(index) != board[i][j]) {
            return false;
        }
        visited[i][j] = true;
        // Perform DFS in all four directions
        if (dfs(board, i + 1, j, m, n, visited, index + 1, word)
                || dfs(board, i - 1, j, m, n, visited, index + 1, word)
                || dfs(board, i, j + 1, m, n, visited, index + 1, word)
                || dfs(board, i, j - 1, m, n, visited, index + 1, word)) {
            return true;
        }
        return visited[i][j] = false;
    }

    public static void main(String[] args) {
        final WordSearch wordSearch = new WordSearch();

        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(wordSearch.exist(board, word));

        word = "SEE";
        System.out.println(wordSearch.exist(board, word));

        word = "ABCB";
        System.out.println(wordSearch.exist(board, word));
    }
}
