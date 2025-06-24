package org.redquark.extras.solutions.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class WindowedAverageStore {

    // Map to store key and the pair of different values at different timestamps
    private final Map<String, TreeMap<Long, Integer>> entries;
    // Min heap to store the entries sorted on timestamp
    private final Queue<Entry> minHeap;
    // TTL for the entry
    private final long ttl;
    // Total sum of elements
    private int sum;
    // Count of unexpired elements
    private int count;

    public WindowedAverageStore(long ttl) {
        this.entries = new HashMap<>();
        this.minHeap = new PriorityQueue<>(Comparator.comparingLong(entry -> entry.timestamp));
        this.ttl = ttl;
        this.sum = 0;
        this.count = 0;
    }

    public void put(String key, int value, long timestamp) {
        // Get timeMap against the key
        final TreeMap<Long, Integer> timeMap = this.entries.computeIfAbsent(key, _ -> new TreeMap<>());
        // Get the existing value against the timestamp if any
        final Integer existingValue = timeMap.get(timestamp);
        timeMap.put(timestamp, value);
        if (existingValue != null) {
            this.sum -= existingValue;
        } else {
            this.count++;
        }
        this.sum += value;
        this.minHeap.offer(new Entry(key, value, timestamp));
    }

    public Integer get(String key, long timestamp) {
        if (!this.entries.containsKey(key)) {
            return null;
        }
        final TreeMap<Long, Integer> timeMap = this.entries.get(key);
        // Get the floor entry
        Map.Entry<Long, Integer> floorEntry = timeMap.floorEntry(timestamp);
        if (floorEntry == null || floorEntry.getKey() <= timestamp - this.ttl) {
            return null;
        }
        return floorEntry.getValue();
    }

    public double getAverage(long timestamp) {
        // Expire stale entries
        expireStaleEntries(timestamp);
        return this.count == 0 ? 0.0 : this.sum * 1.0 / this.count;
    }

    private void expireStaleEntries(long timestamp) {
        while (!this.minHeap.isEmpty() && this.minHeap.peek().timestamp <= timestamp - this.ttl) {
            final Entry expiredEntry = this.minHeap.remove();
            // Get the timeMap for this entry
            final TreeMap<Long, Integer> timeMap = this.entries.get(expiredEntry.key);
            if (timeMap != null) {
                // Get the integer value corresponding to it
                final Integer value = timeMap.get(expiredEntry.timestamp);
                if (value != null && value.equals(expiredEntry.value)) {
                    this.count--;
                    this.sum -= value;
                    timeMap.remove(timestamp);
                    if (timeMap.isEmpty()) {
                        this.entries.remove(expiredEntry.key);
                    }
                }
            }
        }
    }

    record Entry(String key, int value, long timestamp) {
    }

    public static void main(String[] args) {
        WindowedAverageStore windowedAverageStore = new WindowedAverageStore(5000);

        windowedAverageStore.put("a", 10, 1000);
        windowedAverageStore.put("b", 20, 2000);
        System.out.println("Average @2500: " + windowedAverageStore.getAverage(2500)); // 15.0
        windowedAverageStore.put("a", 30, 6000); // replaces previous "a" entry

        System.out.println("Get a@7000: " + windowedAverageStore.get("a", 7000));      // 30
        System.out.println("Get b@7000: " + windowedAverageStore.get("b", 7000));      // null (expired)
        System.out.println("Average @7000: " + windowedAverageStore.getAverage(7000)); // 30.0
    }
}
