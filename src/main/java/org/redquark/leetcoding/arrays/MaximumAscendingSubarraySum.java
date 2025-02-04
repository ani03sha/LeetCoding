package org.redquark.leetcoding.arrays;

public class MaximumAscendingSubarraySum {

    public int maxAscendingSum(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Maximum ascending sum
        int maxSum = nums[0];
        int currentSum = nums[0];
        // Process all elements in the array
        for (int i = 1; i < n; i++) {
            while (i > 0 && i < n && nums[i - 1] < nums[i]) {
                currentSum += nums[i];
                i++;
            }
            maxSum = Math.max(maxSum, currentSum);
            if (i < n) {
                currentSum = nums[i];
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        final MaximumAscendingSubarraySum maximumAscendingSubarraySum = new MaximumAscendingSubarraySum();

        int[] nums = new int[]{10, 20, 30, 5, 10, 50};
        System.out.println(maximumAscendingSubarraySum.maxAscendingSum(nums));

        nums = new int[]{10, 20, 30, 40, 50};
        System.out.println(maximumAscendingSubarraySum.maxAscendingSum(nums));

        nums = new int[]{12, 17, 15, 13, 10, 11, 12};
        System.out.println(maximumAscendingSubarraySum.maxAscendingSum(nums));
    }
}
