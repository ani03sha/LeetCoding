package org.redquark.leetcoding.dynamicprogramming;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        // Special case
        if (amount < 0 || coins == null || coins.length == 0) {
            return 0;
        }
        // Lookup table to store number of ways to make certain amount
        final int[] lookup = new int[amount + 1];
        // There's only one way to make 0 amount
        lookup[0] = 1;
        // Calculate for every value of amount
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (coin <= i) {
                    lookup[i] += lookup[i - coin];
                }
            }
        }
        return lookup[amount];
    }

    public static void main(String[] args) {
        final CoinChangeII coinChangeII = new CoinChangeII();

        int amount = 5;
        int[] coins = new int[]{1, 2, 5};
        System.out.println(coinChangeII.change(amount, coins));

        amount = 3;
        coins = new int[]{2};
        System.out.println(coinChangeII.change(amount, coins));

        amount = 10;
        coins = new int[]{10};
        System.out.println(coinChangeII.change(amount, coins));
    }
}
