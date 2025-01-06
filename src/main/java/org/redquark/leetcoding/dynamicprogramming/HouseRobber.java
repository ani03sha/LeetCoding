package org.redquark.leetcoding.dynamicprogramming;

public class HouseRobber {

    public int rob(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Base cases
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // Lookup table to store maximum loot until i-th house
        final int[] lookup = new int[n + 1];
        // Base initialization
        lookup[0] = nums[0];
        lookup[1] = Math.max(nums[0], nums[1]);
        // Process remaining positions
        for (int i = 2; i < n; i++) {
            lookup[i] = Math.max(lookup[i - 1], nums[i] + lookup[i - 2]);
        }
        return lookup[n - 1];
    }

    public static void main(String[] args) {
        final HouseRobber houseRobber = new HouseRobber();

        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(houseRobber.rob(nums));

        nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(houseRobber.rob(nums));
    }
}
