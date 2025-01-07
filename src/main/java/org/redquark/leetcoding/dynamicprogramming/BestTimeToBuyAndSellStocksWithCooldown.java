package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStocksWithCooldown {

    public int maxProfit(int[] prices) {
        // Special case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        final int n = prices.length;
        // Arrays to keep track of buying and selling prices
        final int[] buyingPrices = new int[n];
        final int[] sellingPrices = new int[n];
        Arrays.fill(buyingPrices, Integer.MIN_VALUE);
        buyingPrices[0] = -prices[0];
        // Process the prices array
        for (int i = 1; i < n; i++) {
            // We can either buy stock on the current day or we skip it, to honor
            // one day cooldown period
            buyingPrices[i] = Math.max(buyingPrices[i - 1], (i > 1 ? sellingPrices[i - 2] : 0) - prices[i]);
            sellingPrices[i] = Math.max(sellingPrices[i - 1], buyingPrices[i - 1] + prices[i]);
        }
        return sellingPrices[n - 1];
    }

    public static void main(String[] args) {
        final BestTimeToBuyAndSellStocksWithCooldown bestTimeToBuyAndSellStocksWithCooldown = new BestTimeToBuyAndSellStocksWithCooldown();

        int[] prices = new int[]{1, 2, 3, 0, 2};
        System.out.println(bestTimeToBuyAndSellStocksWithCooldown.maxProfit(prices));

        prices = new int[]{1};
        System.out.println(bestTimeToBuyAndSellStocksWithCooldown.maxProfit(prices));
    }
}
