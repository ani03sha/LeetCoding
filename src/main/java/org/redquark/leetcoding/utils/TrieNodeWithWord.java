package org.redquark.leetcoding.utils;

public class TrieNodeWithWord {
    public String word;
    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        this.children = new TrieNodeWithWord[26];
    }
}
