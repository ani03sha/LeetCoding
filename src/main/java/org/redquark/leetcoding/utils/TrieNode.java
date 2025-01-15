package org.redquark.leetcoding.utils;

public class TrieNode {
    public char data;
    public TrieNode[] children;
    public boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26];
    }

    public TrieNode(char data) {
        this.data = data;
        this.children = new TrieNode[26];
    }
}
