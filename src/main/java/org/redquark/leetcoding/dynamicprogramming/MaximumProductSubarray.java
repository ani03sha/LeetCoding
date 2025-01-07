package org.redquark.leetcoding.dynamicprogramming;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid inputs!");
        }
        // Variable to keep track of maximum product overall
        int globalMax = nums[0];
        // Variable to keep track of max product until index i
        int localMax = nums[0];
        // Variable to keep track of min product until index i
        int localMin = nums[0];
        // Process the remaining elements in the array
        for (int i = 1; i < nums.length; i++) {
            // Store localMax for future calculations
            final int temp = localMax;
            // Update localMax, localMin and globalMax
            localMax = Math.max(nums[i], Math.max(nums[i] * temp, nums[i] * localMin));
            localMin = Math.min(nums[i], Math.min(nums[i] * temp, nums[i] * localMin));
            globalMax = Math.max(localMax, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        final MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();

        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println(maximumProductSubarray.maxProduct(nums));

        nums = new int[]{-2, 0, -1};
        System.out.println(maximumProductSubarray.maxProduct(nums));
    }
}
