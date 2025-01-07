package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Lookup table to store LIS until the index i
        final int[] lookup = new int[n];
        // Every element is an LIS of 1
        Arrays.fill(lookup, 1);
        // Longest LIS
        int longestLIS = 1;
        // Process the array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lookup[i] = Math.max(lookup[i], 1 + lookup[j]);
                }
            }
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
