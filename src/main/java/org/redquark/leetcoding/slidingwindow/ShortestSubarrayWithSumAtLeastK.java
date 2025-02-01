package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        final int n = nums.length;
        // Array to store the prefix sum
        final long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // Deque to store the potential start pointers
        final Deque<Integer> deque = new ArrayDeque<>();
        // Shortest length
        int shortestLength = Integer.MAX_VALUE;
        // Process elements in the array
        for (int i = 0; i <= n; i++) {
            // Check if we have found and index in the queue that can be treated as
            // the start pointer and from that index to current index i the sum is
            // greater than target k
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                shortestLength = Math.min(shortestLength, i - deque.removeFirst());
            }
            // If the prefix sum until the current index i is less than or equal to the
            // prefix sum until the index that is in the rear of the queue, then that
            // index is not going to help further
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.removeLast();
            }
            // Add current index to the deque
            deque.addLast(i);
        }
        return shortestLength == Integer.MAX_VALUE ? -1 : shortestLength;
    }

    public static void main(String[] args) {
        final ShortestSubarrayWithSumAtLeastK shortestSubarrayWithSumAtLeastK = new ShortestSubarrayWithSumAtLeastK();

        int[] nums = new int[]{1};
        int k = 1;
        System.out.println(shortestSubarrayWithSumAtLeastK.shortestSubarray(nums, k));

        nums = new int[]{1, 2};
        k = 4;
        System.out.println(shortestSubarrayWithSumAtLeastK.shortestSubarray(nums, k));

        nums = new int[]{2, -1, 2};
        k = 3;
        System.out.println(shortestSubarrayWithSumAtLeastK.shortestSubarray(nums, k));

        nums = new int[]{56, -21, 56, 35, -9};
        k = 61;
        System.out.println(shortestSubarrayWithSumAtLeastK.shortestSubarray(nums, k));
    }
}
