package org.redquark.leetcoding.bitmanipulation;

import java.util.Arrays;

public class CountingBits {

    public int[] countBits(int n) {
        // Array to store result
        final int[] result = new int[n + 1];
        // Process all elements
        for (int i = 1; i <= n; i++) {
            int setBits = 0;
            int x = i;
            while (x > 0) {
                x &= (x - 1);
                setBits++;
            }
            result[i] = setBits;
        }
        return result;
    }

    public static void main(String[] args) {
        final CountingBits countingBits = new CountingBits();

        System.out.println(Arrays.toString(countingBits.countBits(2)));
        System.out.println(Arrays.toString(countingBits.countBits(5)));
    }
}
