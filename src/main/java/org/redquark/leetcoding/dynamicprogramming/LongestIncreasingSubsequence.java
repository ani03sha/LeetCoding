package org.redquark.leetcoding.dynamicprogramming;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Lookup table to store the LIS until the current index
        final int[] lookup = new int[n];
        lookup[0] = 1;
        // Longest LIS
        int longestLIS = 1;
        for (int i = 1; i < n; i++) {
            // Since every element is an LIS of length 1
            lookup[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lookup[i] = Math.max(lookup[i], 1 + lookup[j]);
                }
            }
            // Updated longest LIS length, if required
            longestLIS = Math.max(longestLIS, lookup[i]);
        }
        return longestLIS;
    }

    public static void main(String[] args) {
        final LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();

        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

        nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
