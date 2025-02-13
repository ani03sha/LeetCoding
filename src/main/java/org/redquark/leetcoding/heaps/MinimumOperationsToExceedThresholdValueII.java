package org.redquark.leetcoding.heaps;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumOperationsToExceedThresholdValueII {

    public int minOperations(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // Heap to store the elements of the array
        final Queue<Long> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer((long) num);
        }
        // If the smallest element is already greater than or equal to k
        if (minHeap.peek() >= k) {
            return 0;
        }
        // Operations needed
        int operations = 0;
        // Process until root of heap is greater than or equal to k
        while (minHeap.size() > 1 && minHeap.peek() < k) {
            // Get two smallest elements
            final long x = minHeap.remove(); // Equivalent to min(x, y)
            final long y = minHeap.remove(); // Equivalent to max(x, y)
            minHeap.offer(2 * x + y);
            operations++;
        }
        return operations;
    }

    public static void main(String[] args) {
        final MinimumOperationsToExceedThresholdValueII minimumOperationsToExceedThresholdValueII = new MinimumOperationsToExceedThresholdValueII();

        int[] nums = new int[]{2, 11, 10, 1, 3};
        int k = 10;
        System.out.println(minimumOperationsToExceedThresholdValueII.minOperations(nums, k));

        nums = new int[]{1, 1, 2, 4, 9};
        k = 20;
        System.out.println(minimumOperationsToExceedThresholdValueII.minOperations(nums, k));

        nums = new int[]{999999999, 999999999, 999999999};
        k = 1000000000;
        System.out.println(minimumOperationsToExceedThresholdValueII.minOperations(nums, k));

        nums = new int[]{1000000000, 999999999, 1000000000, 999999999, 1000000000, 999999999};
        k = 1000000000;
        System.out.println(minimumOperationsToExceedThresholdValueII.minOperations(nums, k));
    }
}
