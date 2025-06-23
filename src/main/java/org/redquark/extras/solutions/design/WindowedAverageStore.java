package org.redquark.extras.solutions.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class WindowedAverageStore {

    // Map to store key and mappings of timestamp and value
    private final Map<String, TreeMap<Long, Integer>> entries;
    // Min heap to store entries sorted on time
    private final Queue<Entry> expiryQueue;
    // TTL of the cache
    private final long ttl;
    // Sum of all unexpired tokens
    private int sum;
    // Count of all unexpired tokens
    private int count;

    public WindowedAverageStore(long ttl) {
        this.entries = new HashMap<>();
        this.expiryQueue = new PriorityQueue<>(Comparator.comparingLong(a -> a.timestamp));
        this.ttl = ttl;
        this.sum = 0;
        this.count = 0;
    }

    public void put(String key, int value, long timestamp) {
        this.entries.computeIfAbsent(key, _ -> new TreeMap<>()).put(timestamp, value);
        this.expiryQueue.offer(new Entry(key, timestamp, value));
        this.sum += value;
        this.count++;
    }

    public Integer get(String key, long timestamp) {
        if (!this.entries.containsKey(key)) {
            return null;
        }
        final TreeMap<Long, Integer> timeMap = this.entries.get(key);
        // Get the floor entry
        final Map.Entry<Long, Integer> floorEntry = timeMap.floorEntry(timestamp);
        if (floorEntry == null || floorEntry.getKey() <= timestamp - this.ttl) {
            return null;
        }
        return floorEntry.getValue();
    }

    public double getAverage(long timestamp) {
        // Expire old entries
        expireOld(timestamp);
        return this.count == 0 ? 0.0 : this.sum * 1.0 / this.count;
    }

    private void expireOld(long timestamp) {
        while (!this.expiryQueue.isEmpty() && this.expiryQueue.peek().timestamp <= timestamp - this.ttl) {
            final Entry expired = this.expiryQueue.remove();
            final TreeMap<Long, Integer> timeMap = this.entries.get(expired.key);
            if (timeMap != null) {
                final Integer value = timeMap.get(expired.timestamp);
                if (value != null && value == expired.value) {
                    this.sum -= value;
                    this.count--;
                    timeMap.remove(expired.timestamp);
                    if (timeMap.isEmpty()) {
                        this.entries.remove(expired.key);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WindowedAverageStore windowedAverageStore = new WindowedAverageStore(5000);

        windowedAverageStore.put("a", 10, 1000);
        windowedAverageStore.put("b", 20, 2000);
        windowedAverageStore.put("a", 30, 6000); // replaces previous "a" entry

        System.out.println("Get a@7000: " + windowedAverageStore.get("a", 7000));      // 30
        System.out.println("Get b@7000: " + windowedAverageStore.get("b", 7000));      // null (expired)
        System.out.println("Average @2500: " + windowedAverageStore.getAverage(2500)); // 15.0
        System.out.println("Average @7000: " + windowedAverageStore.getAverage(7000)); // 30.0
    }

    record Entry(String key, long timestamp, int value) {
    }
}
