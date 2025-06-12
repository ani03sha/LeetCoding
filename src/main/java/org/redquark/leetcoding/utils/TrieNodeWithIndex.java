package org.redquark.leetcoding.utils;

public class TrieNodeWithIndex {

    public final TrieNodeWithIndex[] children;
    public int index;

    public TrieNodeWithIndex() {
        this.children = new TrieNodeWithIndex[26];
        this.index = -1;
    }
}
