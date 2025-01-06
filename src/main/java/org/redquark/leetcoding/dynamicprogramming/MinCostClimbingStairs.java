package org.redquark.leetcoding.dynamicprogramming;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        // Special case
        if (cost == null || cost.length == 0) {
            return 0;
        }
        final int n = cost.length;
        if (n == 1) {
            return cost[0];
        }
        if (n == 2) {
            return Math.min(cost[0], cost[1]);
        }
        // Variables to keep track of last two pointers
        int a = cost[0];
        int b = cost[1];
        int c;
        for (int i = 2; i <= n; i++) {
            c = Math.min(a, b) + (i == n ? 0 : cost[i]);
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        final MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();

        int[] cost = new int[]{10, 15, 20};
        System.out.println(minCostClimbingStairs.minCostClimbingStairs(cost));

        cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs.minCostClimbingStairs(cost));
    }
}
