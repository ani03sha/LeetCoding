package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CountTheNumberOfGoodSubarrays {

    public long countGood(int[] nums, int k) {
        final int n = nums.length;
        // Count of good subarrays
        long goodSubarrays = 0;
        // Current number of pairs
        long currentPairs = 0;
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Map to store frequencies of elements in the array
        final Map<Integer, Integer> frequencies = new HashMap<>();
        // Process all elements in the array
        while (right < n) {
            final int count = frequencies.getOrDefault(nums[right], 0);
            // Adding a number to the subarray creates "count" number
            // of pairs
            currentPairs += count;
            frequencies.put(nums[right], frequencies.getOrDefault(nums[right], 0) + 1);
            // As long as currentPairs >= k, try to slide the window to
            // find other pairs
            while (currentPairs >= k) {
                // All subarrays starting from left to right are good
                goodSubarrays += n - right;
                // Remove the left element from the subarray
                final int leftCount = frequencies.get(nums[left]);
                frequencies.put(nums[left], leftCount - 1);
                currentPairs -= (leftCount - 1);
                left++;
            }
            right++;
        }
        return goodSubarrays;
    }

    public static void main(String[] args) {
        final CountTheNumberOfGoodSubarrays countTheNumberOfGoodSubarrays = new CountTheNumberOfGoodSubarrays();

        int[] nums = new int[]{1, 1, 1, 1, 1};
        int k = 10;
        System.out.println(countTheNumberOfGoodSubarrays.countGood(nums, k));

        nums = new int[]{3, 1, 4, 3, 2, 2, 4};
        k = 2;
        System.out.println(countTheNumberOfGoodSubarrays.countGood(nums, k));
    }
}
