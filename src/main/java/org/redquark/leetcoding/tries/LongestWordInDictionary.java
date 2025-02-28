package org.redquark.leetcoding.tries;

import org.redquark.leetcoding.utils.TrieNodeWithWord;

public class LongestWordInDictionary {

    private TrieNodeWithWord root;
    // String to store the final result
    private String result;

    public String longestWord(String[] words) {
        // Special case
        if (words == null || words.length == 0) {
            return "";
        }
        this.root = new TrieNodeWithWord();
        this.result = "";
        // Insert all words in the Trie
        for (String word : words) {
            insert(word);
        }
        // Perform DFS to find the longest word
        dfs(this.root);
        return result;
    }

    private void dfs(TrieNodeWithWord node) {
        if (node == null) {
            return;
        }
        if (node.word != null) {
            if (node.word.length() > this.result.length()) {
                this.result = node.word;
            } else if (node.word.length() == this.result.length() && node.word.compareTo(this.result) < 0) {
                this.result = node.word;
            }
        }
        for (TrieNodeWithWord child : node.children) {
            if (child != null && child.word != null) {
                dfs(child);
            }
        }
    }

    private void insert(String word) {
        TrieNodeWithWord current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNodeWithWord();
            }
            current = current.children[c - 'a'];
        }
        current.word = word;
    }

    public static void main(String[] args) {
        final LongestWordInDictionary longestWordInDictionary = new LongestWordInDictionary();

        String[] words = new String[]{"w", "wo", "wor", "worl", "world"};
        System.out.println(longestWordInDictionary.longestWord(words));

        words = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(longestWordInDictionary.longestWord(words));
    }
}
