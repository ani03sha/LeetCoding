package org.redquark.leetcoding.dynamicprogramming;

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Create a new array with left and right extremes
        final int[] a = new int[n + 2];
        a[0] = a[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            a[i + 1] = nums[i];
        }
        // Lookup table to store max score
        final int[][] lookup = new int[n + 2][n + 2];
        return dfs(a, 0, n + 1, lookup);
    }

    private int dfs(int[] a, int left, int right, int[][] lookup) {
        if (left > right) {
            return 0;
        }
        if (lookup[left][right] != 0) {
            return lookup[left][right];
        }
        // Total number of coins for current iteration
        int coins = 0;
        for (int i = left + 1; i < right; i++) {
            coins = Math.max(coins, a[left] * a[i] * a[right] + dfs(a, left, i, lookup) + dfs(a, i, right, lookup));
        }
        return lookup[left][right] = coins;
    }

    public static void main(String[] args) {
        final BurstBalloons burstBalloons = new BurstBalloons();

        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(burstBalloons.maxCoins(nums));

        nums = new int[]{1, 5};
        System.out.println(burstBalloons.maxCoins(nums));
    }
}
