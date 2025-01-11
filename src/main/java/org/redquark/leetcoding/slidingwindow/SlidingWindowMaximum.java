package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < k) {
            return new int[]{};
        }
        final int n = nums.length;
        // Array to store the maximum sliding window
        final int[] slidingWindowMax = new int[n - k + 1];
        // Deque to store indices of elements of sliding window
        final Deque<Integer> deque = new ArrayDeque<>();
        // Process elements in the array
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // Remove elements from thr deque which are less than the
            // current elements as they can never be max
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // Add current index to the deque
            deque.offer(i);
            if (i >= k - 1) {
                // noinspection DataFlowIssue
                slidingWindowMax[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return slidingWindowMax;
    }

    public static void main(String[] args) {
        final SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow(nums, k)));

        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow(nums, k)));
    }
}
