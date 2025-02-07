package org.redquark.leetcoding.heaps;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianFromDataStream {

    static class MedianFinder {
        // Min heap to store the median and elements after median
        private final Queue<Integer> minHeap;
        // Max heap to store elements that should come before median
        private final Queue<Integer> maxHeap;

        public MedianFinder() {
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            this.minHeap.offer(num);
            this.maxHeap.offer(minHeap.remove());
            // Adjust the heaps
            if (this.maxHeap.size() > this.minHeap.size()) {
                this.minHeap.offer(this.maxHeap.remove());
            }
        }

        public double findMedian() {
            // If total number of elements in stream are odd
            if (this.minHeap.size() > this.maxHeap.size()) {
                return minHeap.peek();
            }
            // If total number of elements in the stream are even
            return (this.minHeap.peek() + this.maxHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        final MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}
