package org.redquark.leetcoding.greedy;

import java.util.Arrays;

public class MaximumSwap {

    public int maximumSwap(int num) {
        // Convert the number to a character array for easy access
        final char[] digits = String.valueOf(num).toCharArray();
        // Array to store the last index of every digit from 0-9
        final int[] lastIndex = new int[10];
        Arrays.fill(lastIndex, -1);
        for (int i = 0; i < digits.length; i++) {
            lastIndex[digits[i] - '0'] = i;
        }
        // Now, we get the maximum swap by swapping the left most digit
        // with the largest digit to its right.
        // However, if there are multiple occurrences of the largest digit,
        // we swap it with the rightmost occurrence.
        for (int i = 0; i < digits.length; i++) {
            // Check for the largest digit starting from 9 to 0
            for (int j = 9; j > digits[i] - '0'; j--) {
                // If a larger digit is found to the right, swap it
                if (lastIndex[j] > i) {
                    final char temp = digits[i];
                    digits[i] = digits[lastIndex[j]];
                    digits[lastIndex[j]] = temp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        // If no swap it made, return the original number
        return num;
    }

    public static void main(String[] args) {
        final MaximumSwap maximumSwap = new MaximumSwap();

        System.out.println(maximumSwap.maximumSwap(2736));
        System.out.println(maximumSwap.maximumSwap(9973));
    }
}
