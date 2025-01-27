package org.redquark.leetcoding.arrays;

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Global maximum to keep track to maximum sum of subarray for the
        // entire array
        int globalMax = nums[0];
        // Local maximum to keep track of maximum sum until the current index
        int localMax = nums[0];
        // Process remaining elements in the array
        for (int i = 1; i < nums.length; i++) {
            localMax = Math.max(nums[i], localMax + nums[i]);
            globalMax = Math.max(localMax, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        final MaximumSubarray maximumSubarray = new MaximumSubarray();

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maximumSubarray.maxSubArray(nums));

        nums = new int[]{1};
        System.out.println(maximumSubarray.maxSubArray(nums));

        nums = new int[]{5, 4, -1, 7, 8};
        System.out.println(maximumSubarray.maxSubArray(nums));
    }
}
