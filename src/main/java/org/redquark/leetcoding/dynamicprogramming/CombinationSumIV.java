package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Lookup table to store count of combinations to reach i amount
        final int[] lookup = new int[target + 1];
        Arrays.fill(lookup, -1);
        // Perform memoization
        helper(nums, target, lookup);
        return lookup[target];
    }

    private int helper(int[] nums, int target, int[] lookup) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (lookup[target] != -1) {
            return lookup[target];
        }
        // Total combinations for current target value
        int count = 0;
        // Check for all elements in the array
        for (int num : nums) {
            if (target >= num) {
                count += helper(nums, target - num, lookup);
            }
        }
        return lookup[target] = count;
    }

    public static void main(String[] args) {
        final CombinationSumIV combinationSumIV = new CombinationSumIV();

        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        System.out.println(combinationSumIV.combinationSum4(nums, target));

        nums = new int[]{9};
        target = 3;
        System.out.println(combinationSumIV.combinationSum4(nums, target));
    }
}
