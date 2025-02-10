package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.LRUCacheNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Map to store the key and their respective LRUCacheNode
    private final Map<Integer, LRUCacheNode> entries;
    // Doubly linked list represented by head and tail,
    // These are the fake nodes
    private final LRUCacheNode head;
    private final LRUCacheNode tail;
    // Capacity of the cache
    private final int capacity;

    public LRUCache(int capacity) {
        this.entries = new HashMap<>();
        this.head = new LRUCacheNode();
        this.tail = new LRUCacheNode();
        this.capacity = capacity;
        // Connect head and tail
        this.head.next = tail;
        this.tail.previous = head;
    }

    public int get(int key) {
        // Check if the key is present in the cache
        if (this.entries.containsKey(key)) {
            // Get node corresponding to that key
            final LRUCacheNode node = this.entries.get(key);
            // Get value from this node
            final int value = node.value;
            // Since, this node is accessed, we need to make it
            // most recently used
            remove(node);
            add(node);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // Check if the node already exists in the cache
        if (this.entries.containsKey(key)) {
            // Get node corresponding to the key
            final LRUCacheNode node = this.entries.get(key);
            // Update the value at the node
            node.value = value;
            // Since, this node is accessed, we need to make it
            // most recently used
            remove(node);
            add(node);
        }
        // If this is a new key-value entry in the cache
        else {
            // Check for the capacity of the cache
            if (this.entries.size() >= this.capacity) {
                // Remove least recently used entry
                this.entries.remove(tail.previous.key);
                remove(tail.previous);
            }
            final LRUCacheNode node = new LRUCacheNode();
            node.key = key;
            node.value = value;
            this.entries.put(key, node);
            // Make this node more recently used
            add(node);
        }
    }

    private void add(LRUCacheNode node) {
        // Get next of the current head
        final LRUCacheNode nextHead = this.head.next;
        this.head.next = node;
        node.previous = this.head;
        node.next = nextHead;
        nextHead.previous = node;
    }

    private void remove(LRUCacheNode node) {
        final LRUCacheNode next = node.next;
        final LRUCacheNode previous = node.previous;
        previous.next = next;
        next.previous = previous;
    }

    public static void main(String[] args) {
        final LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
