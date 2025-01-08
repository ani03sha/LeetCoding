package org.redquark.leetcoding.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Lookup table to store combination and ways
        final Map<String, Integer> lookup = new HashMap<>();
        // Evaluate ways
        return evaluate(nums, 0, target, lookup);
    }

    private int evaluate(int[] nums, int index, int target, Map<String, Integer> lookup) {
        if (index >= nums.length) {
            return target == 0 ? 1 : 0;
        }
        // Create key for the map
        final String key = index + "-" + target;
        if (lookup.containsKey(key)) {
            return lookup.get(key);
        }
        // Calculate ways
        final int ways = evaluate(nums, index + 1, target - nums[index], lookup)
                + evaluate(nums, index + 1, target + nums[index], lookup);
        lookup.put(key, ways);
        return lookup.get(key);
    }

    public static void main(String[] args) {
        final TargetSum targetSum = new TargetSum();

        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(targetSum.findTargetSumWays(nums, target));

        nums = new int[]{1};
        target = 1;
        System.out.println(targetSum.findTargetSumWays(nums, target));
    }
}
