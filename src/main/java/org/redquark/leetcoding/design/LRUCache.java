package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.LRUCacheNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Map to store the entries of the cache
    private final Map<Integer, LRUCacheNode> entries;
    // Head and tail of the doubly linked list
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
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }

    public int get(int key) {
        // If the node exists in the cache, return the value
        if (this.entries.containsKey(key)) {
            final LRUCacheNode node = this.entries.get(key);
            final int value = node.value;
            // Since, we have accessed the node, we need to remove
            // it from its original position and move it to the front
            removeNode(node);
            addToFront(node);
            return value;
        }
        // If the entry doesn't exist, we return -1
        return -1;
    }

    public void put(int key, int value) {
        // If the entry already exists in the cache, we update the value
        if (this.entries.containsKey(key)) {
            final LRUCacheNode node = this.entries.get(key);
            // Update the value
            node.value = value;
            // Since, we have accessed the node, we need to remove
            // it from its original position and move it to the front
            removeNode(node);
            addToFront(node);
        }
        // If the entry doesn't exist, we create a new one
        else {
            final LRUCacheNode newEntry = new LRUCacheNode();
            newEntry.key = key;
            newEntry.value = value;
            // If the cache has reached its capacity, we need to remove
            // the least recently used entry (just before tail)
            if (this.entries.size() >= this.capacity) {
                final LRUCacheNode nodeToBeRemoved = this.tail.previous;
                removeNode(nodeToBeRemoved);
                // Remove from entries as well
                this.entries.remove(nodeToBeRemoved.key);
            }
            // Add the newEntry to the cache
            this.entries.put(key, newEntry);
            // Add this node to the front
            addToFront(newEntry);
        }
    }

    private void removeNode(LRUCacheNode node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void addToFront(LRUCacheNode node) {
        final LRUCacheNode currentFront = this.head.next;
        this.head.next = node;
        node.previous = this.head;
        currentFront.previous = node;
        node.next = currentFront;
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
