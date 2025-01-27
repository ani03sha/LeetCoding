package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Dummy head and tail nodes
    private final Entry head;
    private final Entry tail;
    // Capacity of the cache
    private final int capacity;
    // Map that represents cache
    private final Map<Integer, Entry> cache;

    public LRUCache(int capacity) {
        this.head = new Entry();
        this.tail = new Entry();
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        // Connecting head and tail to each other
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }

    public int get(int key) {
        // Check if the entry exists
        if (cache.containsKey(key)) {
            final Entry entry = this.cache.get(key);
            // Remove and add the node to the front of the list
            remove(entry);
            add(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // Get the node from the given key
        final Entry entry = cache.get(key);
        if (entry != null) {
            // Update the value
            entry.value = value;
            // Remove and add the node to the front of the list
            remove(entry);
            add(entry);
        } else {
            // If we have exhausted the capacity of the cache
            if (this.cache.size() == this.capacity) {
                // Remove oldest node
                this.cache.remove(tail.previous.key);
                remove(tail.previous);
            }
            final Entry newEntry = new Entry();
            newEntry.key = key;
            newEntry.value = value;
            this.cache.put(key, newEntry);
            // Add to the front of the linked list
            add(newEntry);
        }
    }

    private void add(Entry entry) {
        final Entry nextHead = head.next;
        head.next = entry;
        entry.previous = head;
        entry.next = nextHead;
        nextHead.previous = entry;
    }

    private void remove(Entry entry) {
        final Entry next = entry.next;
        Entry previous = entry.previous;
        previous.next = next;
        next.previous = previous;
    }

    static class Entry {
        int key;
        int value;
        Entry previous;
        Entry next;
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
