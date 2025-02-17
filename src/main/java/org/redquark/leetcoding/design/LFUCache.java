package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    private final Map<Integer, Integer> keyToValueMappings;
    private final Map<Integer, Integer> keyToCountMappings;
    private final Map<Integer, LinkedHashSet<Integer>> countToKeysMappings;
    private final int capacity;
    private int minimum;

    public LFUCache(int capacity) {
        this.keyToValueMappings = new HashMap<>();
        this.keyToCountMappings = new HashMap<>();
        this.countToKeysMappings = new HashMap<>();
        this.capacity = capacity;
        // Minimum accessed item
        this.minimum = -1;
    }

    public int get(int key) {
        // Check if the key exists in the cache
        if (this.keyToValueMappings.containsKey(key)) {
            final int value = this.keyToValueMappings.get(key);
            // Since this item is accessed, we increased the number
            // of times this key is accessed
            int currentCount = this.keyToCountMappings.get(key);
            // Remove this count from the countToKeysMappings
            this.countToKeysMappings.get(currentCount).remove(key);
            // If the currentCount is equal to the minimum and there
            // are no keys w.r.t. the currentCount in the countToKeysMappings
            if (currentCount == this.minimum && this.countToKeysMappings.get(currentCount).isEmpty()) {
                this.minimum++;
            }
            addCount(key, currentCount + 1);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // Check if the element already exists, we update its value
        if (this.keyToValueMappings.containsKey(key)) {
            this.keyToValueMappings.put(key, value);
            // Update key's count
            get(key);
            return;
        }
        // If the capacity of the cache is reached, we need to evict
        // the least frequently used element
        if (this.keyToValueMappings.size() >= this.capacity) {
            removeKey(this.countToKeysMappings.get(minimum).getFirst());
        }
        // Reset the minimum
        this.minimum = -1;
        // Add new key and count
        addCount(key, minimum);
        this.keyToValueMappings.put(key, value);
    }

    private void removeKey(int key) {
        this.countToKeysMappings.get(this.minimum).remove(key);
        this.keyToValueMappings.remove(key);
    }

    private void addCount(int key, int count) {
        this.keyToCountMappings.put(key, count);
        this.countToKeysMappings.computeIfAbsent(count, _ -> new LinkedHashSet<>()).add(key);
    }

    public static void main(String[] args) {
        final LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfuCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfuCache.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfuCache.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfuCache.get(2));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfuCache.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfuCache.get(1));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfuCache.get(4));      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}
