package org.redquark.extras.solutions.design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache<K, V> {

    private final int capacity;
    private final ConcurrentHashMap<K, Node<K, V>> entries;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final Lock lock;

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        this.entries = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();

        // Dummy head and tail nodes
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public V get(K key) {
        Node<K, V> node = entries.get(key);
        if (node == null) {
            return null;
        }

        // Move the accessed node to the front (most recently used)
        lock.lock();
        try {
            removeNode(node);
            addToFront(node);
        } finally {
            lock.unlock();
        }

        return node.value;
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (entries.containsKey(key)) {
                Node<K, V> node = entries.get(key);
                node.value = value;
                removeNode(node);
                addToFront(node);
            } else {
                if (entries.size() >= capacity) {
                    Node<K, V> lru = tail.prev;
                    if (lru != head) {
                        removeNode(lru);
                        entries.remove(lru.key);
                    }
                }
                Node<K, V> newNode = new Node<>(key, value);
                entries.put(key, newNode);
                addToFront(newNode);
            }
        } finally {
            lock.unlock();
        }
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
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
