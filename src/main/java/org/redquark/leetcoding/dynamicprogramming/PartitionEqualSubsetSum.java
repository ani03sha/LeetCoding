package org.redquark.leetcoding.dynamicprogramming;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid inputs!");
        }
        final int n = nums.length;
        // Calculate the sum of all the elements in the array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // If the sum is not even, we cannot partition
        if (sum % 2 != 0) {
            return false;
        }
        // Target sum
        sum /= 2;
        // Boolean array to store if a sum is possible or not
        final Boolean[][] lookup = new Boolean[n + 1][sum + 1];
        return helper(nums, 0, sum, lookup);
    }

    private boolean helper(int[] nums, int index, int target, Boolean[][] lookup) {
        if (target == 0) {
            return true;
        }
        if (index >= nums.length || target < 0) {
            return false;
        }
        if (lookup[index][target] != null) {
            return lookup[index][target];
        }
        return lookup[index][target] = helper(nums, index + 1, target - nums[index], lookup)
                || helper(nums, index + 1, target, lookup);
    }

    public static void main(String[] args) {
        final PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();

        int[] nums = new int[]{1, 5, 11, 5};
        System.out.println(partitionEqualSubsetSum.canPartition(nums));

        nums = new int[]{1, 2, 3, 5};
        System.out.println(partitionEqualSubsetSum.canPartition(nums));
    }
}
