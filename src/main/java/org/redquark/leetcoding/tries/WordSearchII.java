package org.redquark.leetcoding.tries;

import org.redquark.leetcoding.utils.TrieNodeWithWord;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        // List to store all the words on the board
        final List<String> result = new ArrayList<>();
        // Special case
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return result;
        }
        // Build the Trie
        final TrieNodeWithWord root = buildTrie(words);
        // Dimensions of the board
        final int m = board.length;
        final int n = board[0].length;
        // Traverse through every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, m, n, root, result);
            }
        }
        return result;
    }

    private TrieNodeWithWord buildTrie(String[] words) {
        // Create root of the Trie
        final TrieNodeWithWord root = new TrieNodeWithWord();
        for (String word : words) {
            // Reference of the root node for traversal
            TrieNodeWithWord current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNodeWithWord();
                }
                current = current.children[c - 'a'];
            }
            current.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, int m, int n, TrieNodeWithWord node, List<String> result) {
        // Base case
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        // Current character
        final char c = board[i][j];
        // Check if we have already visited this cell
        if (c == '#') {
            return;
        }
        // Check if we don't have children for the current node
        if (node.children[c - 'a'] == null) {
            return;
        }
        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // To avoid duplicates
        }
        // Mark current cell visited
        board[i][j] = '#';
        // Perform DFS in all four directions
        dfs(board, i + 1, j, m, n, node, result);
        dfs(board, i - 1, j, m, n, node, result);
        dfs(board, i, j + 1, m, n, node, result);
        dfs(board, i, j - 1, m, n, node, result);
        board[i][j] = c;
    }

    public static void main(String[] args) {
        final WordSearchII wordSearchII = new WordSearchII();

        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(wordSearchII.findWords(board, words));
    }
}
