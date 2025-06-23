package org.redquark.extras.solutions.design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache<K, V> {

    private final int capacity;
    private final ConcurrentHashMap<K, Node<K, V>> entries;
    private final DoublyLinkedList<K, V> dll;
    private final Lock lock;

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        this.entries = new ConcurrentHashMap<>();
        this.dll = new DoublyLinkedList<>();
        this.lock = new ReentrantLock();
    }

    public V get(K key) {
        final Node<K, V> node = this.entries.get(key);
        if (node == null) {
            return null;
        }
        this.lock.lock();
        try {
            this.dll.moveToFront(node);
        } finally {
            lock.unlock();
        }
        return node.value;
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (this.entries.containsKey(key)) {
                final Node<K, V> node = this.entries.get(key);
                node.value = value;
                this.dll.moveToFront(node);
            } else {
                if (this.entries.size() >= this.capacity) {
                    final Node<K, V> node = this.dll.removeLast();
                    if (node != null) {
                        this.entries.remove(node.key);
                    }
                }
                final Node<K, V> newEntry = new Node<>(key, value);
                this.dll.addToFront(newEntry);
                this.entries.put(key, newEntry);
            }
        } finally {
            this.lock.unlock();
        }
    }

    static class DoublyLinkedList<K, V> {
        private final Node<K, V> head;
        private final Node<K, V> tail;

        DoublyLinkedList() {
            this.head = new Node<>(null, null);
            this.tail = new Node<>(null, null);
            this.head.next = this.tail;
            this.tail.previous = this.head;
        }

        void addToFront(Node<K, V> node) {
            node.next = this.head.next;
            this.head.next.previous = node;
            node.previous = this.head;
            this.head.next = node;
        }

        public void remove(Node<K, V> node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }

        public void moveToFront(Node<K, V> node) {
            remove(node);
            addToFront(node);
        }

        public Node<K, V> removeLast() {
            if (this.tail.previous == null) {
                return null;
            }
            Node<K, V> node = this.tail.previous;
            remove(node);
            return node;
        }
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> previous;
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
