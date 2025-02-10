package org.redquark.leetcoding.utils;

public class LRUCacheNode {
    public int key;
    public int value;
    public LRUCacheNode next;
    public LRUCacheNode previous;

    public LRUCacheNode() {
    }
}