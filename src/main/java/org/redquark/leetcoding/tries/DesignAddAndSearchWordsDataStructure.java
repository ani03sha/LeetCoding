package org.redquark.leetcoding.tries;

public class DesignAddAndSearchWordsDataStructure {

    static class WordDictionary {

        private final TrieNode root;

        public WordDictionary() {
            this.root = new TrieNode();
        }

        public void addWord(String word) {
            // Reference to the root node
            TrieNode temp = this.root;
            // Process the word
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new TrieNode(c);
                }
                temp = temp.children[c - 'a'];
            }
            temp.isWord = true;
        }

        public boolean search(String word) {
            return searchInNode(word, 0, this.root);
        }

        private boolean searchInNode(String word, int index, TrieNode node) {
            if (node == null) {
                return false;
            }
            if (index == word.length()) {
                return node.isWord;
            }
            // Current character
            final char c = word.charAt(index);
            if (c == '.') {
                // Check in all possible children
                for (TrieNode child : node.children) {
                    if (searchInNode(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                return searchInNode(word, index + 1, node.children[c - 'a']);
            }
        }

        static class TrieNode {
            char data;
            TrieNode[] children;
            boolean isWord;

            TrieNode() {
                this.children = new TrieNode[26];
            }

            TrieNode(char data) {
                this.data = data;
                this.children = new TrieNode[26];
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}
