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

    public boolean existSpaceOptimized(char[][] board, String word) {
        // Special case
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }
        // Dimensions of the board
        final int m = board.length;
        final int n = board[0].length;
        // Process every cell on the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Perform backtracking
                if (backtrack(board, i, j, m, n, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int i, int j, int m, int n, String word, int index) {
        // Base condition
        if (index == word.length()) {
            return true;
        }
        // Check for cell validity
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index) || board[i][j] == '#') {
            return false;
        }
        // Current character
        final char c = board[i][j];
        // Mark current cell as visited
        board[i][j] = '#';
        // Perform DFS in all four directions
        final boolean w = backtrack(board, i - 1, j, m, n, word, index + 1);
        final boolean x = backtrack(board, i + 1, j, m, n, word, index + 1);
        final boolean y = backtrack(board, i, j - 1, m, n, word, index + 1);
        final boolean z = backtrack(board, i, j + 1, m, n, word, index + 1);
        // Reset character
        board[i][j] = c;
        return w || x || y || z;
    }

    public static void main(String[] args) {
        final WordSearch wordSearch = new WordSearch();

        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(wordSearch.exist(board, word));
        System.out.println(wordSearch.existSpaceOptimized(board, word));

        word = "SEE";
        System.out.println(wordSearch.exist(board, word));
        System.out.println(wordSearch.existSpaceOptimized(board, word));

        word = "ABCB";
        System.out.println(wordSearch.exist(board, word));
        System.out.println(wordSearch.existSpaceOptimized(board, word));
    }
}
