package org.redquark.leetcoding.arrays;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public int longestMonotonicSubarray(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Longest increasing subarray length
        int longestIncreasingSubarray = 1;
        // Longest decreasing subarray length
        int longestDecreasingSubarray = 1;
        // Longest monotonic length
        int maxCount = 1;
        for (int i = 1; i < n; i++) {
            // For increasing subarray
            if (nums[i] > nums[i - 1]) {
                longestIncreasingSubarray++;
                longestDecreasingSubarray = 1;
            }
            // For decreasing subarray
            else if (nums[i] < nums[i - 1]) {
                longestIncreasingSubarray = 1;
                longestDecreasingSubarray++;
            }
            // For equal elements
            else {
                longestIncreasingSubarray = 1;
                longestDecreasingSubarray = 1;
            }
            maxCount = Math.max(maxCount, Math.max(longestIncreasingSubarray, longestDecreasingSubarray));
        }
        return maxCount;
    }

    public static void main(String[] args) {
        final LongestStrictlyIncreasingOrStrictlyDecreasingSubarray longestStrictlyIncreasingOrStrictlyDecreasingSubarray
                = new LongestStrictlyIncreasingOrStrictlyDecreasingSubarray();

        int[] nums = new int[]{1, 4, 3, 3, 2};
        System.out.println(longestStrictlyIncreasingOrStrictlyDecreasingSubarray.longestMonotonicSubarray(nums));

        nums = new int[]{3, 3, 3, 3};
        System.out.println(longestStrictlyIncreasingOrStrictlyDecreasingSubarray.longestMonotonicSubarray(nums));

        nums = new int[]{3, 2, 1};
        System.out.println(longestStrictlyIncreasingOrStrictlyDecreasingSubarray.longestMonotonicSubarray(nums));
    }
}
