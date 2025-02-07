package org.redquark.leetcoding.dynamicprogramming;

public class MinimumCostForTickets {

    public int minCostTickets(int[] days, int[] costs) {
        // Special case
        if (days == null || days.length == 0 || costs == null || costs.length == 0) {
            return 0;
        }
        final int n = days.length;
        // Boolean array to check if a current day is a travel day or not
        final boolean[] isTravelDay = new boolean[days[n - 1] + 1];
        // Mark travel days
        for (int day : days) {
            isTravelDay[day] = true;
        }
        // Lookup table to store minimum cost of travel on a certain day
        final int[] lookup = new int[31];
        // Process days
        for (int i = days[0]; i <= days[n - 1]; i++) {
            // If not a travel day
            if (!isTravelDay[i]) {
                lookup[i % 31] = lookup[(i - 1) % 31];
            }
            // If a travel day
            else {
                // Calculate all days costs
                final int oneDayCost = costs[0] + lookup[Math.max(0, (i - 1) % 31)];
                int oneWeekCost = costs[1] + lookup[Math.max(0, (i - 7) % 31)];
                int oneMonthCost = costs[2] + lookup[Math.max(0, (i - 30) % 31)];
                // Find min all costs
                lookup[i % 31] = Math.min(oneDayCost, Math.min(oneWeekCost, oneMonthCost));
            }
        }
        return lookup[days[n - 1] % 31];
    }

    public static void main(String[] args) {
        final MinimumCostForTickets minimumCostForTickets = new MinimumCostForTickets();

        int[] days = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs = new int[]{2, 7, 15};
        System.out.println(minimumCostForTickets.minCostTickets(days, costs));

        days = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        costs = new int[]{2, 7, 15};
        System.out.println(minimumCostForTickets.minCostTickets(days, costs));
    }
}
