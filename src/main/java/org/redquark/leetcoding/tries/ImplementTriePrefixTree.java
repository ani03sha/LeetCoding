package org.redquark.leetcoding.tries;

import org.redquark.leetcoding.utils.TrieNode;

public class ImplementTriePrefixTree {

    static class Trie {

        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            // Check if the word is already present
            if (search(word)) {
                return;
            }
            // Reference to the root node for traversal
            TrieNode temp = root;
            // Process the string
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new TrieNode(c);
                }
                temp = temp.children[c - 'a'];
            }
            temp.isWord = true;
        }

        public boolean search(String word) {
            // Reference to the root node for traversal
            TrieNode temp = root;
            // Process the string
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    return false;
                }
                temp = temp.children[c - 'a'];
            }
            return temp.isWord;
        }

        public boolean startsWith(String prefix) {
            // Reference to the root node for traversal
            TrieNode temp = root;
            // Process the string
            for (char c : prefix.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    return false;
                }
                temp = temp.children[c - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
