package org.redquark.leetcoding.greedy;

import java.util.Arrays;

public class MaximumSwap {

    public int maximumSwap(int num) {
        // Convert the number to the char array
        final char[] digits = String.valueOf(num).toCharArray();
        // Total number of digits
        final int n = digits.length;
        // Array to store the index of maximum value to the right
        // of the current value
        final int[] maxRightIndex = new int[n];
        Arrays.setAll(maxRightIndex, i -> i);
        // Traverse the digit array from right to left excluding
        // the right most digit
        for (int i = n - 2; i >= 0; i--) {
            if (digits[i] <= digits[maxRightIndex[i + 1]]) {
                maxRightIndex[i] = maxRightIndex[i + 1];
            }
        }
        // Traverse from right to left to determine the first digit
        // that is smaller than the max digit to its right
        for (int i = 0; i < n; i++) {
            final int maxIndex = maxRightIndex[i];
            // If such a digit is found, swap it with the max digit
            // to its right
            if (digits[i] < digits[maxIndex]) {
                final char temp = digits[maxIndex];
                digits[maxIndex] = digits[i];
                digits[i] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(digits));
    }

    public static void main(String[] args) {
        final MaximumSwap maximumSwap = new MaximumSwap();

        System.out.println(maximumSwap.maximumSwap(2736));
        System.out.println(maximumSwap.maximumSwap(9973));
    }
}
