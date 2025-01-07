package org.redquark.leetcoding.dynamicprogramming;

public class HouseRobberII {

    public int rob(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // We can either choose first and second last houses or second and last houses
        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] nums, int left, int right) {
        // Variables to keep track of two previous maximum values
        int excludePrevious = 0; // Excluded previous house
        int includePrevious = 0; // Included previous house
        // Process houses from left to the right
        for (int i = left; i <= right; i++) {
            // Amount when we include the current house
            int includeCurrent = excludePrevious + nums[i];
            // Update new maximum if previous house is excluded
            excludePrevious = Math.max(includePrevious, excludePrevious);
            // Update includePrevious for next iteration;
            includePrevious = includeCurrent;
        }
        return Math.max(includePrevious, excludePrevious);
    }

    public static void main(String[] args) {
        final HouseRobberII houseRobberII = new HouseRobberII();

        int[] nums = new int[]{2, 3, 2};
        System.out.println(houseRobberII.rob(nums));

        nums = new int[]{1, 2, 3, 1};
        System.out.println(houseRobberII.rob(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(houseRobberII.rob(nums));
    }
}
