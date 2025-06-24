package org.redquark.extras.solutions.design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache<K, V> {

    // Capacity of the cache
    private final int capacity;
    // Map to store all mappings of keys and their entry nodes
    private final ConcurrentHashMap<K, Entry<K, V>> entries;
    // Lock for thread safety
    private final Lock lock;
    // Head and tail of the entry nodes (DLL)
    private final Entry<K, V> head;
    private final Entry<K, V> tail;

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        this.entries = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.head = new Entry<>(null, null);
        this.tail = new Entry<>(null, null);
        // Connect both head and tail
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }

    public V get(K key) {
        final Entry<K, V> entry = this.entries.get(key);
        // If the key doesn't exist in the cache
        if (entry == null) {
            return null;
        }
        this.lock.lock();
        try {
            // Remove entry from its original position
            removeEntry(entry);
            // Add entry to the front of DLL
            addToFront(entry);
        } finally {
            this.lock.unlock();
        }
        return entry.value;
    }

    public void put(K key, V value) {
        this.lock.lock();
        try {
            // If the key already exists, we just update the value
            if (this.entries.containsKey(key)) {
                final Entry<K, V> entry = this.entries.get(key);
                entry.value = value;
                // Since this node is accessed, we need to move it to
                // the front after removing it from its original position
                removeEntry(entry);
                addToFront(entry);
            }
            // If this is a new key
            else {
                final Entry<K, V> newEntry = new Entry<>(key, value);
                // If the cache is full, we evict the least recently
                // accessed entry
                if (this.entries.size() >= this.capacity) {
                    final Entry<K, V> entryToBeRemoved = this.tail.previous;
                    removeEntry(entryToBeRemoved);
                    this.entries.remove(entryToBeRemoved.key);
                }
                this.entries.put(key, newEntry);
                addToFront(newEntry);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void addToFront(Entry<K,V> entry) {
        final Entry<K, V> currentFrontEntry = this.head.next;
        this.head.next = entry;
        entry.next = currentFrontEntry;
        entry.previous = this.head;
        currentFrontEntry.previous = entry;
    }

    private void removeEntry(Entry<K,V> entry) {
        entry.previous.next = entry.next;
        entry.next.previous = entry.previous;
    }

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K,V> previous;
        Entry<K,V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentLRUCache<Integer, String> cache = new ConcurrentLRUCache<>(3);

        // --- Single-threaded Test ---
        System.out.println("Single-threaded Test");
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        System.out.println(cache.get(2)); // Two
        cache.put(4, "Four");             // Should evict key 1
        System.out.println(cache.get(1)); // null
        System.out.println(cache.get(3)); // Three
        System.out.println(cache.get(4)); // Four

        // --- Multi-threaded Test ---
        System.out.println("\nMulti-threaded Test");

        Runnable writer = () -> {
            for (int i = 5; i < 10; i++) {
                cache.put(i, "Val" + i);
                System.out.println("Put: " + i + " -> Val" + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
            }
        };

        Runnable reader = () -> {
            for (int i = 1; i < 10; i++) {
                String val = cache.get(i);
                System.out.println("Get: " + i + " -> " + val);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ignored) {
                }
            }
        };

        Thread writerThread = new Thread(writer);
        Thread readerThread = new Thread(reader);

        writerThread.start();
        readerThread.start();

        writerThread.join();
        readerThread.join();

        // Final state
        System.out.println("\nFinal state:");
        for (int i = 1; i < 10; i++) {
            String val = cache.get(i);
            if (val != null) {
                System.out.println("Key: " + i + ", Value: " + val);
            }
        }
    }
}
