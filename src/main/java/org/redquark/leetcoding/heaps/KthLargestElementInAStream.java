package org.redquark.leetcoding.heaps;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInAStream {

    static class KthLargest {

        // Min heap to store the test scores
        private final Queue<Integer> minHeap;
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.minHeap = new PriorityQueue<>();
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            // If there are less than k elements in the heap,
            // can just add the current element to the heap.
            if (this.minHeap.size() < this.k) {
                this.minHeap.offer(val);
                return this.minHeap.size() == this.k ? this.minHeap.peek() : -1;
            }
            // If the value to be added is greater than the one
            // at the peek, we will first remove the root (as it
            // is the smallest element), and then add current value.
            if (this.minHeap.peek() != null && val > this.minHeap.peek()) {
                this.minHeap.remove();
                this.minHeap.offer(val);
            }
            return !this.minHeap.isEmpty() ? this.minHeap.peek() : -1;
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = new int[]{4, 5, 8, 2};
        final KthLargest kthLargest = new KthLargest(k, nums);
        System.out.println(kthLargest.add(3)); // return 4
        System.out.println(kthLargest.add(5)); // return 5
        System.out.println(kthLargest.add(10)); // return 5
        System.out.println(kthLargest.add(9)); // return 8
        System.out.println(kthLargest.add(4)); // return 8
    }
}
