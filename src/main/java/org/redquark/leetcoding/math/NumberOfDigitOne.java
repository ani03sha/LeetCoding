package org.redquark.leetcoding.math;

public class NumberOfDigitOne {

    public int countDigitOne(int n) {
        // Total number of ones
        int ones = 0;
        // Process numbers from 1 to n
        for (long i = 1; i <= n; i *= 10) {
            final long divisor = i * 10;
            ones += (int) ((n / divisor) * i + Math.min(i, Math.max(n % divisor - i + 1, 0)));
        }
        return ones;
    }

    public static void main(String[] args) {
        final NumberOfDigitOne numberOfDigitOne = new NumberOfDigitOne();

        System.out.println(numberOfDigitOne.countDigitOne(13)); // 6
        System.out.println(numberOfDigitOne.countDigitOne(0)); // 0
        System.out.println(numberOfDigitOne.countDigitOne(100)); // 21
    }
}
