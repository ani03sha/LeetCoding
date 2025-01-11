package org.redquark.leetcoding.slidingwindow;

public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid inputs!");
        }
        final int n = nums.length;
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Sum of the current window
        int currentSum = 0;
        // Size of the smallest window
        int smallestSize = Integer.MAX_VALUE;
        // Process the array
        while (right < n) {
            while (right < n && currentSum < target) {
                currentSum += nums[right];
                right++;
            }
            // Now, squeeze the window, if possible
            while (left < right && currentSum >= target) {
                smallestSize = Math.min(smallestSize, right - left);
                currentSum -= nums[left];
                left++;
            }
        }
        return smallestSize == Integer.MAX_VALUE ? 0 : smallestSize;
    }

    public static void main(String[] args) {
        final MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();

        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println(minimumSizeSubarraySum.minSubArrayLen(target, nums));

        nums = new int[]{1, 4, 4};
        target = 4;
        System.out.println(minimumSizeSubarraySum.minSubArrayLen(target, nums));

        nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        target = 11;
        System.out.println(minimumSizeSubarraySum.minSubArrayLen(target, nums));
    }
}
