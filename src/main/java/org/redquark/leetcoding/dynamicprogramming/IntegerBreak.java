package org.redquark.leetcoding.dynamicprogramming;

public class IntegerBreak {

    public int integerBreak(int n) {
        // Special case
        if (n < 2) {
            return 0;
        }
        // Lookup table to store the max product to break a number = i
        final int[] lookup = new int[n + 1];
        lookup[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                lookup[i] = Math.max(lookup[i], Math.max(j, lookup[j]) * Math.max(i - j, lookup[i - j]));
            }
        }
        return lookup[n];
    }

    public static void main(String[] args) {
        final IntegerBreak integerBreak = new IntegerBreak();

        System.out.println(integerBreak.integerBreak(2));
        System.out.println(integerBreak.integerBreak(12));
        System.out.println(integerBreak.integerBreak(42));
        System.out.println(integerBreak.integerBreak(58));
    }
}
