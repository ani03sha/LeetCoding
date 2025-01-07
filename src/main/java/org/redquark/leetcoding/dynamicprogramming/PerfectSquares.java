package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;

public class PerfectSquares {

    public int numSquares(int n) {
        // Find the square root of n to determine the range of integers
        // that can be used to make n
        final int squareRoot = (int) Math.sqrt(n);
        // Lookup table to store number of perfect squares that sum to n = i
        final int[] lookup = new int[n + 1];
        Arrays.fill(lookup, Integer.MAX_VALUE);
        lookup[0] = 0;
        // Process the natural numbers upto n
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= squareRoot; j++) {
                if (j * j <= i) {
                    int difference = lookup[i - j * j];
                    if (difference != Integer.MAX_VALUE) {
                        lookup[i] = Math.min(lookup[i], 1 + difference);
                    }
                }
            }
        }
        return lookup[n];
    }

    public static void main(String[] args) {
        final PerfectSquares perfectSquares = new PerfectSquares();

        System.out.println(perfectSquares.numSquares(56));
        System.out.println(perfectSquares.numSquares(6747));
        System.out.println(perfectSquares.numSquares(9835));
    }
}
