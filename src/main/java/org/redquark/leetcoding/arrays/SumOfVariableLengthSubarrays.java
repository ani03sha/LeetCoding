package org.redquark.leetcoding.arrays;

public class SumOfVariableLengthSubarrays {

    public int subarraySum(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Variable to keep track of start index
        int start = 0;
        // Total sum of subarrays
        int sum = nums[start];
        // Process for remaining elements
        for (int i = 1; i < n; i++) {
            start = Math.max(0, i - nums[i]);
            for (int j = start; j <= i; j++) {
                sum += nums[j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        final SumOfVariableLengthSubarrays sumOfVariableLengthSubarrays = new SumOfVariableLengthSubarrays();

        int[] nums = new int[]{2, 3, 1};
        System.out.println(sumOfVariableLengthSubarrays.subarraySum(nums));

        nums = new int[]{3, 1, 1, 2};
        System.out.println(sumOfVariableLengthSubarrays.subarraySum(nums));
    }
}
