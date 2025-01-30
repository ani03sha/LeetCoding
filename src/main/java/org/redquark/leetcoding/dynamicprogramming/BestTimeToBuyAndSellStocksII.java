package org.redquark.leetcoding.dynamicprogramming;

public class BestTimeToBuyAndSellStocksII {

    public int maxProfit(int[] prices) {
        // Special case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // Variable to keep track of maximum profit
        int maxProfit = 0;
        // Process all stock prices
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        final BestTimeToBuyAndSellStocksII bestTimeToBuyAndSellStocksII = new BestTimeToBuyAndSellStocksII();

        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(bestTimeToBuyAndSellStocksII.maxProfit(prices));

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(bestTimeToBuyAndSellStocksII.maxProfit(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(bestTimeToBuyAndSellStocksII.maxProfit(prices));
    }
}
