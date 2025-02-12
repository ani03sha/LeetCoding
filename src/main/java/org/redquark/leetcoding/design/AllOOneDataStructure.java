package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.Bucket;

import java.util.HashMap;
import java.util.Map;

public class AllOOneDataStructure {

    static class AllOne {

        // Map to store frequency of a certain key
        private final Map<String, Integer> keyToCountMappings;
        // Map to store a bucket that stores keys with a certain count
        private final Map<Integer, Bucket> countToBucketMappings;
        // Dummy head and tail nodes of a doubly linked list
        private final Bucket head;
        private final Bucket tail;

        public AllOne() {
            this.keyToCountMappings = new HashMap<>();
            this.countToBucketMappings = new HashMap<>();
            this.head = new Bucket(Integer.MIN_VALUE);
            this.tail = new Bucket(Integer.MAX_VALUE);
            this.head.next = this.tail;
            this.tail.previous = this.head;
        }

        public void inc(String key) {
            // Get the current count for the key
            final int currentCount = this.keyToCountMappings.getOrDefault(key, 0);
            // Update the map for the current key
            this.keyToCountMappings.put(key, currentCount + 1);
            // Get the node corresponding to the current count
            final Bucket currentBucket = this.countToBucketMappings.get(currentCount);
            // Get the new node corresponding to new count
            Bucket newBucket = this.countToBucketMappings.get(currentCount + 1);
            // If there's no newBucket till now, we create it
            if (newBucket == null) {
                newBucket = new Bucket(currentCount + 1);
                countToBucketMappings.put(currentCount + 1, newBucket);
                // Add this newBucket after the currentBucket;
                insertAfter(currentBucket, newBucket);
            }
            // Add the key to the set of newBucket's keys
            newBucket.keys.add(key);
            // Remove key from the currentBucket's set of keys
            if (currentBucket != null) {
                removeKeyFromBucket(currentBucket, key);
            }
        }

        public void dec(String key) {
            // Get the count of the key
            final int currentCount = this.keyToCountMappings.get(key);
            // If there's only one key, we just remove it
            if (currentCount == 1) {
                this.keyToCountMappings.remove(key);
            } else {
                this.keyToCountMappings.put(key, currentCount - 1);
            }
            // Get the bucket corresponding to the current count
            final Bucket currentBucket = this.countToBucketMappings.get(currentCount);
            // Get the bucket corresponding to the updated count
            Bucket newBucket = this.countToBucketMappings.get(currentCount - 1);
            if (currentCount > 1 && newBucket == null) {
                newBucket = new Bucket(currentCount - 1);
                this.countToBucketMappings.put(currentCount - 1, newBucket);
                // Add the newBucket before currentBucket
                insertBefore(currentBucket, newBucket);
            }
            if (currentCount > 1) {
                newBucket.keys.add(key);
            }
            // Remove key from the currentBucket because it is updated
            removeKeyFromBucket(currentBucket, key);
        }

        public String getMaxKey() {
            return this.tail.previous == this.head ? "" : this.tail.previous.keys.iterator().next();
        }

        public String getMinKey() {
            return this.head.next == this.tail ? "" : this.head.next.keys.iterator().next();
        }

        private void removeKeyFromBucket(Bucket currentBucket, String key) {
            currentBucket.keys.remove(key);
            // If there are no keys empty
            if (currentBucket.keys.isEmpty()) {
                removeBucket(currentBucket);
                this.countToBucketMappings.remove(currentBucket.frequency);
            }
        }

        private void removeBucket(Bucket currentBucket) {
            currentBucket.previous.next = currentBucket.next;
            currentBucket.next.previous = currentBucket.previous;
        }

        private void insertAfter(Bucket currentBucket, Bucket newBucket) {
            if (currentBucket == null) {
                currentBucket = this.head;
            }
            newBucket.next = currentBucket.next;
            newBucket.previous = currentBucket;
            currentBucket.next.previous = newBucket;
            currentBucket.next = newBucket;
        }

        private void insertBefore(Bucket currentBucket, Bucket newBucket) {
            insertAfter(currentBucket.previous, newBucket);
        }
    }

    public static void main(String[] args) {
        final AllOne allOne = new AllOne();

        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "hello"
        allOne.dec("hello");
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "leet"
    }
}
