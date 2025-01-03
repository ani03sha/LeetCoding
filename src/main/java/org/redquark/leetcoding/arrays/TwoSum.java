package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        // Map to store the element and its index
        final Map<Integer, Integer> mappings = new HashMap<>();
        // Process the array
        for (int i = 0; i < nums.length; i++) {
            if (mappings.containsKey(nums[i])) {
                return new int[]{i, mappings.get(nums[i])};
            }
            mappings.put(target - nums[i], i);
        }
        // Should never reach here
        throw new IllegalArgumentException("Invalid inputs!");
    }

    public static void main(String[] args) {
        final TwoSum twoSum = new TwoSum();

        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum.twoSum(nums, target)));

        nums = new int[]{3, 2, 4};
        target = 6;
        System.out.println(Arrays.toString(twoSum.twoSum(nums, target)));

        nums = new int[]{4, 4};
        target = 8;
        System.out.println(Arrays.toString(twoSum.twoSum(nums, target)));
    }
}
