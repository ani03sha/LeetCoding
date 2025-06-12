package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class TallestBillboard {

    public int tallestBillboard(int[] rods) {
        final int n = rods.length;
        // Sum of all rods
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        // Lookup table to store the maximum height of a billboard
        // when i rods are considered with difference of j
        final int[][] lookup = new int[n + 1][sum + 1];
        Arrays.stream(lookup).forEach(row -> Arrays.fill(row, Integer.MIN_VALUE / 2));
        lookup[0][0] = 0; // No rods considered with 0 height difference
        int totalHeight = 0;
        // Process all rods
        for (int i = 1; i <= n; i++) {
            final int currentRod = rods[i - 1];
            totalHeight += currentRod;
            // Check for all possible height differences
            for (int j = 0; j <= totalHeight; j++) {
                // Case 1: Don't use the current rod
                lookup[i][j] = lookup[i - 1][j];
                // Case 2: Add rod to the taller pole
                if (j >= currentRod) {
                    lookup[i][j] = Math.max(lookup[i][j], lookup[i - 1][j - currentRod]);
                }
                // Case 3: Add rod to the shorted pole
                if (j + currentRod <= totalHeight) {
                    lookup[i][j] = Math.max(lookup[i][j], lookup[i - 1][j + currentRod] + currentRod);
                }
                // Case 4: Current rod makes up the height difference
                if (j < currentRod) {
                    lookup[i][j] = Math.max(lookup[i][j], lookup[i - 1][currentRod - j] + currentRod - j);
                }
            }
        }
        return lookup[n][0];
    }

    public static void main(String[] args) {
        final TallestBillboard tallestBillboard = new TallestBillboard();

        int[] rods = {1, 2, 3, 6};
        int result = tallestBillboard.tallestBillboard(rods);
        System.out.println("The maximum height of the billboard is: " + result);

        rods = new int[]{1, 2, 3, 4, 5, 6};
        result = tallestBillboard.tallestBillboard(rods);
        System.out.println("The maximum height of the billboard is: " + result);

        rods = new int[]{1, 2};
        result = tallestBillboard.tallestBillboard(rods);
        System.out.println("The maximum height of the billboard is: " + result);
    }
}
