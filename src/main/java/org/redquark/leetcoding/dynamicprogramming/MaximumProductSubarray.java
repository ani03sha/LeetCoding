package org.redquark.leetcoding.dynamicprogramming;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Maximum subarray product when the entire array is under consideration
        int globalMaxProduct = nums[0];
        // Maximum subarray product when a subarray is under consideration
        int localMaxProduct = nums[0];
        // Minimum subarray product when a subarray is under consideration
        int localMinProduct = nums[0];
        // Process all elements in the array
        for (int i = 1; i < nums.length; i++) {
            // Store localMaxProduct in a temp variable for later use
            final int temp = localMaxProduct;
            // Update localMaxProduct after considering current element
            localMaxProduct = Math.max(nums[i], Math.max(nums[i] * temp, nums[i] * localMinProduct));
            // Update localMinProduct after considering the current element
            localMinProduct = Math.min(nums[i], Math.min(nums[i] * temp, nums[i] * localMinProduct));
            // Update globalMaxProduct after considering subarray ending at current index
            globalMaxProduct = Math.max(localMaxProduct, globalMaxProduct);
        }
        return globalMaxProduct;
    }

    public static void main(String[] args) {
        final MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();

        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println(maximumProductSubarray.maxProduct(nums));

        nums = new int[]{-2, 0, -1};
        System.out.println(maximumProductSubarray.maxProduct(nums));
    }
}
