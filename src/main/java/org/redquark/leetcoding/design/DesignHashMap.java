package org.redquark.leetcoding.design;

public class DesignHashMap {

    static class MyHashMap {

        // A random prime number for hash calculation. This number should
        // not be too small to avoid frequent hash collisions, and it is
        // also should not be too large to avoid huge memory allocation.
        private static final int PRIME = 8011;
        // Array of nodes to store the key value pairs
        private final Node[] entries;

        public MyHashMap() {
            this.entries = new Node[PRIME];
        }

        public void put(int key, int value) {
            // Get bucket location based on key
            final int bucketLocation = getBucketLocation(key);
            // Get the node at the bucket location
            Node node = this.entries[bucketLocation];
            // If node is null then we can directly store the value at this location
            if (node == null) {
                this.entries[bucketLocation] = new Node(key, value, null);
                return;
            }
            // If the node is not null, then we either update the value of create
            // a linked list at the current location
            while (node != null) {
                if (node.key == key) {
                    node.value = value;
                    return;
                }
                // Else move on the next node
                node = node.next;
            }
            // At this point add a new node at the front
            this.entries[bucketLocation] = new Node(key, value, this.entries[bucketLocation]);
        }

        public int get(int key) {
            // Check if the value exists for the key or not
            final int bucketLocation = getBucketLocation(key);
            Node node = this.entries[bucketLocation];
            if (node == null) {
                return -1;
            }
            // Else we move on to the nodes where key matches
            while (node != null) {
                if (node.key == key) {
                    return node.value;
                }
                node = node.next;
            }
            return -1;
        }

        public void remove(int key) {
            // Get the bucket location and corresponding node
            final int bucketLocation = getBucketLocation(key);
            Node node = this.entries[bucketLocation];
            // If node is null, then there is nothing to remove
            if (node == null) {
                return;
            }
            // Else move in the list to search for the node
            if (node.key == key) {
                this.entries[bucketLocation] = node.next;
                return;
            }
            while (node != null && node.next != null) {
                if (node.next.key == key) {
                    node.next = node.next.next;
                }
                node = node.next;
            }
        }

        private int getBucketLocation(int key) {
            return Integer.hashCode(key) % PRIME;
        }
    }

    static class Node {
        final int key;
        int value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        final MyHashMap myHashMap = new MyHashMap();

        myHashMap.put(1, 1);
        myHashMap.put(2, 2);
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(3));
        myHashMap.put(2, 1);
        System.out.println(myHashMap.get(2));
        myHashMap.remove(2);
        System.out.println(myHashMap.get(2));
    }
}
