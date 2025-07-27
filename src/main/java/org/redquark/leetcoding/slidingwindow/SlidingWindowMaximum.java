package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // Edge case
        if (nums == null || nums.length < k) {
            return new int[]{};
        }
        final int n = nums.length;
        // Array to store the sliding window
        final int[] maxOfSlidingWindow = new int[n - k + 1];
        // Monotonic deque for storing indices w.r.t a sliding window
        final Deque<Integer> window = new ArrayDeque<>();
        // Process all elements in the array
        for (int i = 0; i < n; i++) {
            // Remove elements from deque that are out of window bounds
            if (!window.isEmpty() && window.peekFirst() < i - k + 1) {
                window.removeFirst();
            }
            // Remove elements from the deque that are smaller than the
            // current elements as they can never be the maximum for that window
            while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
                window.removeLast();
            }
            // Add current index to the deque
            window.offer(i);
            // When window size is reached
            if (i >= k - 1) {
                maxOfSlidingWindow[i - k + 1] = nums[window.peekFirst()];
            }
        }
        return maxOfSlidingWindow;
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
