package org.redquark.leetcoding.dynamicprogramming;

public class PaintHouseII {

    public int minCostII(int[][] costs) {
        // Total number of houses
        final int m = costs.length;
        // Total number of colors
        final int n = costs[0].length;
        // Array to store previous house's cost as a starting point
        int[] previousCosts = costs[0].clone();
        // Process all houses
        for (int i = 1; i < m; i++) {
            // Costs of the current house
            int[] currentCosts = costs[i].clone();
            // Iterate through each color for the current house
            for (int j = 0; j < n; j++) {
                // Minimum cost for this house
                int minCost = Integer.MAX_VALUE;
                // Find the minimum cost of painting the previous house
                // with a different color
                for (int k = 0; k < n; k++) {
                    // Skip for the same color
                    if (k == j) {
                        continue;
                    }
                    minCost = Math.min(minCost, previousCosts[k]);
                }
                currentCosts[j] += minCost;
            }
            previousCosts = currentCosts;
        }
        int minCost = previousCosts[0];
        for (int i = 1; i < n; i++) {
            minCost = Math.min(minCost, previousCosts[i]);
        }
        return minCost;
    }

    public static void main(String[] args) {
        final PaintHouseII paintHouseII = new PaintHouseII();

        int[][] costs = new int[][]{{1, 5, 3}, {2, 9, 4}};
        System.out.println(paintHouseII.minCostII(costs));

        costs = new int[][]{{1, 3}, {2, 4}};
        System.out.println(paintHouseII.minCostII(costs));
    }
}
