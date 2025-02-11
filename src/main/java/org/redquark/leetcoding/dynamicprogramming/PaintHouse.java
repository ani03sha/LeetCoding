package org.redquark.leetcoding.dynamicprogramming;

public class PaintHouse {

    public int minCost(int[][] costs) {
        // Cost for each of the color choice
        int red = 0;
        int blue = 0;
        int green = 0;
        // Process all costs
        for (int[] cost : costs) {
            // Previous colored costs for each color
            int previousRed = red;
            int previousBlue = blue;
            int previousGreen = green;
            // If we choose red color for the current house, then we can choose
            // minimum of green or blue for the previous house, and so on
            red = cost[0] + Math.min(previousBlue, previousGreen);
            blue = cost[1] + Math.min(previousRed, previousGreen);
            green = cost[2] + Math.min(previousRed, previousBlue);
        }
        // Find the minimum of all three choices
        return Math.min(red, Math.min(blue, green));
    }

    public static void main(String[] args) {
        final PaintHouse paintHouse = new PaintHouse();

        int[][] costs = new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println(paintHouse.minCost(costs));

        costs = new int[][]{{7, 6, 2}};
        System.out.println(paintHouse.minCost(costs));
    }
}
