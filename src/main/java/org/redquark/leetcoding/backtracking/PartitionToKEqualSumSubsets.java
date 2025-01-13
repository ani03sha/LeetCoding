package org.redquark.leetcoding.backtracking;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < k) {
            return false;
        }
        // Find the sum of all the elements in the array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // If the sum is not divisible by k
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        // Target sum to reach for each subset
        int target = sum / k;
        // Array to store visited elements
        final boolean[] visited = new boolean[nums.length];
        // Perform backtrack
        return backtrack(nums, 0, 0, visited, target, k);
    }

    private boolean backtrack(int[] nums, int index, int subsetSum, boolean[] visited, int target, int k) {
        if (k == 0) {
            return true;
        }
        if (subsetSum == target) {
            return backtrack(nums, 0, 0, visited, target, k - 1);
        }
        for (int i = index; i < nums.length; i++) {
            if (visited[i] || subsetSum + nums[i] > target) {
                continue;
            }
            visited[i] = true;
            if (backtrack(nums, i + 1, subsetSum + nums[i], visited, target, k)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        final PartitionToKEqualSumSubsets partitionToKEqualSumSubsets = new PartitionToKEqualSumSubsets();

        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(partitionToKEqualSumSubsets.canPartitionKSubsets(nums, k));

        nums = new int[]{1, 2, 3, 4};
        k = 3;
        System.out.println(partitionToKEqualSumSubsets.canPartitionKSubsets(nums, k));
    }
}
