package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        final int n = digits.length;
        // Process the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // We will reach here when we have a carry
            digits[i] = 0;
        }
        // New digit array to incorporate remaining carry
        final int[] newDigits = new int[n + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    public static void main(String[] args) {
        final PlusOne plusOne = new PlusOne();

        int[] digits = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(plusOne.plusOne(digits)));

        digits = new int[]{4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOne.plusOne(digits)));

        digits = new int[]{9};
        System.out.println(Arrays.toString(plusOne.plusOne(digits)));
    }
}
