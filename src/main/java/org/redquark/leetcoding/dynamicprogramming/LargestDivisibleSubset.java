package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        // List to store the largest divisible subset
        final List<Integer> subset = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return subset;
        }
        final int n = nums.length;
        // Sort the array
        Arrays.sort(nums);
        // Lookup table to store largest subset upto that index
        final int[] lookup = new int[n];
        // Array to store the previous index of the number that
        // perfectly divides the current number
        final int[] previous = new int[n];
        // Length of the longest subset that satisfies the condition
        int longestSubsetLength = 0;
        int maxIndex = 0;
        // Process all elements in the array
        for (int i = 0; i < n; i++) {
            // Since every element is divisible by itself
            lookup[i] = 1;
            previous[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (lookup[i] < 1 + lookup[j]) {
                        lookup[i] = 1 + lookup[j];
                        previous[i] = j;
                    }
                }
            }
            if (lookup[i] > lookup[maxIndex]) {
                maxIndex = i;
            }
        }
        while (maxIndex != -1) {
            subset.addFirst(nums[maxIndex]);
            maxIndex = previous[maxIndex];
        }
        return subset;
    }

    public static void main(String[] args) {
        final LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();

        int[] nums = new int[]{1, 2, 3};
        System.out.println(largestDivisibleSubset.largestDivisibleSubset(nums));

        nums = new int[]{1, 2, 4, 8};
        System.out.println(largestDivisibleSubset.largestDivisibleSubset(nums));
    }
}
