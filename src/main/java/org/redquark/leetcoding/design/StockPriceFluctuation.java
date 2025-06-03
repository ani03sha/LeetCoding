package org.redquark.leetcoding.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class StockPriceFluctuation {

    static class StockPriceWithTreeMap {

        // Variable to keep track of the latest timestamp
        private int latestTimestamp;
        // Mappings of timestamp and stock price
        private final Map<Integer, Integer> timeToPriceMappings;
        // TreeMap to store stock prices and their respective counts
        private final TreeMap<Integer, Integer> priceCountMappings;

        public StockPriceWithTreeMap() {
            this.latestTimestamp = 0;
            this.timeToPriceMappings = new HashMap<>();
            this.priceCountMappings = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            // Update the latest timestamp
            this.latestTimestamp = Math.max(this.latestTimestamp, timestamp);
            // If the current price is a correction
            if (this.timeToPriceMappings.containsKey(timestamp)) {
                // Old price
                final int oldPrice = this.timeToPriceMappings.get(timestamp);
                // Decrement its count from priceCountMappings
                this.priceCountMappings.put(oldPrice, this.priceCountMappings.get(oldPrice) - 1);
                if (this.priceCountMappings.get(oldPrice) == 0) {
                    this.priceCountMappings.remove(oldPrice);
                }
            }
            // Add new mapping to timeToPriceMappings
            this.timeToPriceMappings.put(timestamp, price);
            // Increment the count of price
            this.priceCountMappings.put(price, this.priceCountMappings.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return this.timeToPriceMappings.get(this.latestTimestamp);
        }

        public int maximum() {
            return this.priceCountMappings.lastKey();
        }

        public int minimum() {
            return this.priceCountMappings.firstKey();
        }
    }

    static class StockPriceWithHeaps {
        // Variable to keep track of the latest timestamp
        private int latestTimestamp;
        // Mappings of timestamp and respective stock price
        private final Map<Integer, Integer> timeToPriceMappings;
        // Max and min heaps to store a [stock price, timestamp] pair
        private final Queue<int[]> maxHeap;
        private final Queue<int[]> minHeap;

        public StockPriceWithHeaps() {
            this.latestTimestamp = 0;
            this.timeToPriceMappings = new HashMap<>();
            this.maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            this.minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        }

        public void update(int timestamp, int price) {
            // Update the latestTimestamp, if required
            this.latestTimestamp = Math.max(this.latestTimestamp, timestamp);
            this.timeToPriceMappings.put(timestamp, price);
            this.maxHeap.offer(new int[]{price, timestamp});
            this.minHeap.offer(new int[]{price, timestamp});
        }

        public int current() {
            return this.timeToPriceMappings.get(this.latestTimestamp);
        }

        public int maximum() {
            int[] top = this.maxHeap.peek();
            while (this.timeToPriceMappings.get(top[1]) != top[0]) {
                this.maxHeap.remove();
                top = this.maxHeap.peek();
            }
            return top[0];
        }

        public int minimum() {
            int[] top = this.minHeap.peek();
            while (this.timeToPriceMappings.get(top[1]) != top[0]) {
                this.minHeap.remove();
                top = this.minHeap.peek();
            }
            return top[0];
        }
    }

    public static void main(String[] args) {
        // Write test cases to validate the implementation
        StockPriceWithTreeMap stockPriceWithTreeMap = new StockPriceWithTreeMap();
        stockPriceWithTreeMap.update(1, 10);
        stockPriceWithTreeMap.update(2, 5);
        stockPriceWithTreeMap.update(1, 3);
        System.out.println("Current Price: " + stockPriceWithTreeMap.current()); // 5
        System.out.println("Maximum Price: " + stockPriceWithTreeMap.maximum()); // 5
        System.out.println("Minimum Price: " + stockPriceWithTreeMap.minimum()); // 3

        StockPriceWithHeaps stockPriceWithHeaps = new StockPriceWithHeaps();
        stockPriceWithHeaps.update(1, 10);
        stockPriceWithHeaps.update(2, 5);
        stockPriceWithHeaps.update(1, 3);
        System.out.println("Current Price: " + stockPriceWithHeaps.current()); // 5
        System.out.println("Maximum Price: " + stockPriceWithHeaps.maximum()); // 5
        System.out.println("Minimum Price: " + stockPriceWithHeaps.minimum()); // 3
        stockPriceWithHeaps.update(3, 8);
        System.out.println("Current Price: " + stockPriceWithHeaps.current()); // 8
    }
}
