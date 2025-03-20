package org.redquark.leetcoding.binarysearch;

public class MinimumCostToMakeArrayEqual {

    public long minCost(int[] nums, int[] cost) {
        // Find the minimum and maximum values from the array.
        // That will represent the range over which we find a
        // number which we need to converge to
        long left = 1;
        long right = Long.MAX_VALUE;
        for (int num : nums) {
            left = Math.min(num, left);
            right = Math.max(num, right);
        }
        // Minimum cost - starting with 1
        long minCost = findCost(nums, cost, 1);
        // Perform binary search to final the optimal value
        while (left < right) {
            final long middle = left + (right - left) / 2;
            // Calculate costs using middle and middle + 1
            final long cost1 = findCost(nums, cost, middle);
            final long cost2 = findCost(nums, cost, middle + 1);
            // Update minCost
            minCost = Math.min(cost1, cost2);
            if (cost1 < cost2) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return minCost;
    }

    private long findCost(int[] nums, int[] cost, long x) {
        long currentCost = 0;
        for (int i = 0; i < nums.length; i++) {
            currentCost += Math.abs(nums[i] - x) * cost[i];
        }
        return currentCost;
    }

    public static void main(String[] args) {
        final MinimumCostToMakeArrayEqual minimumCostToMakeArrayEqual = new MinimumCostToMakeArrayEqual();

        int[] nums = new int[]{1, 3, 5, 2};
        int[] cost = new int[]{2, 3, 1, 14};
        System.out.println(minimumCostToMakeArrayEqual.minCost(nums, cost));

        nums = new int[]{2, 2, 2, 2, 2};
        cost = new int[]{4, 2, 8, 1, 3};
        System.out.println(minimumCostToMakeArrayEqual.minCost(nums, cost));
    }
}
