package org.redquark.leetcoding.dynamicprogramming;

public class BestTimeToBuyAndSellStocks {

    public int maxProfit(int[] prices) {
        // Special case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // Minimum stock price
        int minPrice = prices[0];
        // Maximum profit so far
        int maxProfit = 0;
        // Process all stocks
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        final BestTimeToBuyAndSellStocks bestTimeToBuyAndSellStocks = new BestTimeToBuyAndSellStocks();

        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(bestTimeToBuyAndSellStocks.maxProfit(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(bestTimeToBuyAndSellStocks.maxProfit(prices));
    }
}
