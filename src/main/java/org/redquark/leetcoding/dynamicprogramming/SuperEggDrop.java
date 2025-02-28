package org.redquark.leetcoding.dynamicprogramming;

public class SuperEggDrop {

    public int superEggDrop(int k, int n) {
        // Lookup table to store minimum moves required at
        // an i-th floor with j eggs
        final int[][] lookup = new int[n + 1][k + 1];
        // Recursively find minimum moves required
        return findMinimumMoves(n, k, lookup);
    }

    private int findMinimumMoves(int floors, int eggs, int[][] lookup) {
        // If no floor
        if (floors < 1) {
            return 0;
        }
        // If we have only one egg
        if (eggs == 1) {
            return floors;
        }
        // If this combination is already computed
        if (lookup[floors][eggs] != 0) {
            return lookup[floors][eggs];
        }
        // Binary search to find the critical floor
        int left = 1;
        int right = floors;
        while (left < right) {
            final int middle = (left + right + 1) / 2;
            // Now, we have two cases
            // 1. Egg breaks
            final int a = findMinimumMoves(middle - 1, eggs - 1, lookup);
            // 2. If the egg doesn't break
            final int b = findMinimumMoves(floors - middle, eggs, lookup);
            // Reduce search space
            if (a <= b) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return lookup[floors][eggs] = 1 + Math.max(findMinimumMoves(left - 1, eggs - 1, lookup),
                findMinimumMoves(floors - left, eggs, lookup));
    }

    public static void main(String[] args) {
        final SuperEggDrop superEggDrop = new SuperEggDrop();

        System.out.println(superEggDrop.superEggDrop(1, 2));
        System.out.println(superEggDrop.superEggDrop(2, 6));
        System.out.println(superEggDrop.superEggDrop(3, 14));
    }
}
