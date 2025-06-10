package org.redquark.leetcoding.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class MaximumGoodSubarraySum {

    public long maximumSubarraySum(int[] nums, int k) {
        final int n = nums.length;
        // Map to store the current element and prefix sum until that element
        final Map<Integer, Long> mappings = new HashMap<>();
        mappings.put(nums[0], 0L);
        // Maximum good subarray sum
        long maxSum = Long.MIN_VALUE;
        // Variable to keep track of the prefix sum
        long prefixSum = 0;
        // Process the array
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            // Check if there's a nums[i] +- k exist in the map
            if (mappings.containsKey(nums[i] - k)) {
                maxSum = Math.max(maxSum, prefixSum - mappings.get(nums[i] - k));
            }
            if (mappings.containsKey(nums[i] + k)) {
                maxSum = Math.max(maxSum, prefixSum - mappings.get(nums[i] + k));
            }
            if (i < n - 1 && (!mappings.containsKey(nums[i + 1]) || mappings.get(nums[i + 1]) > prefixSum)) {
                mappings.put(nums[i + 1], prefixSum);
            }
        }
        return maxSum == Long.MIN_VALUE ? 0 : maxSum;
    }

    public static void main(String[] args) {
        final MaximumGoodSubarraySum maximumGoodSubarraySum = new MaximumGoodSubarraySum();

        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 1;
        System.out.println(maximumGoodSubarraySum.maximumSubarraySum(nums, k)); // 11

        nums = new int[]{-1, 3, 2, 4, 5};
        k = 3;
        System.out.println(maximumGoodSubarraySum.maximumSubarraySum(nums, k)); // 11

        nums = new int[]{-1, -2, -3, -4};
        k = 2;
        System.out.println(maximumGoodSubarraySum.maximumSubarraySum(nums, k)); // -6
    }
}
