package org.redquark.leetcoding.dynamicprogramming;

public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        // Special case
        if (stones == null || stones.length == 0) {
            return 0;
        }
        // Find sum of weights of all the stones
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // Lookup table to store if sum i is possible or not
        boolean[] lookup = new boolean[sum / 2 + 1];
        lookup[0] = true;
        // Maximum value that we can make by maximizing one half
        // of the array
        int max = 0;
        // Process all stones
        for (int stone : stones) {
            // Capture current state of lookup table
            final boolean[] temp = lookup.clone();
            for (int i = stone; i <= sum / 2; i++) {
                if (lookup[i - stone]) {
                    temp[i] = true;
                    max = Math.max(max, i);
                    if (max == sum / 2) {
                        return sum - 2 * max;
                    }
                }
            }
            // Update lookup table with the current state
            lookup = temp;
        }
        return sum - 2 * max;
    }

    public static void main(String[] args) {
        final LastStoneWeightII lastStoneWeightII = new LastStoneWeightII();

        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeightII.lastStoneWeightII(stones));

        stones = new int[]{31, 26, 33, 21, 40};
        System.out.println(lastStoneWeightII.lastStoneWeightII(stones));
    }
}
