package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        // Special case
        if (coins == null || coins.length == 0) {
            return 0;
        }
        // Lookup table to store fewest number of coins to make i amount
        final int[] lookup = new int[amount + 1];
        Arrays.fill(lookup, Integer.MAX_VALUE);
        lookup[0] = 0;
        // Process remaining positions
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    int difference = lookup[i - coin];
                    if (difference != Integer.MAX_VALUE) {
                        lookup[i] = Math.min(lookup[i], difference + 1);
                    }
                }
            }
        }
        return lookup[amount] == Integer.MAX_VALUE ? -1 : lookup[amount];
    }

    public static void main(String[] args) {
        final CoinChange coinChange = new CoinChange();

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(coinChange.coinChange(coins, amount));

        coins = new int[]{2};
        amount = 3;
        System.out.println(coinChange.coinChange(coins, amount));

        coins = new int[]{1};
        amount = 0;
        System.out.println(coinChange.coinChange(coins, amount));
    }
}
