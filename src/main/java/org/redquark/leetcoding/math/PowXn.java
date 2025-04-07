package org.redquark.leetcoding.math;

public class PowXn {

    public double myPowRecursive(double x, int n) {
        // Special case
        if (n == 0) {
            return 1;
        }
        // One special case
        if (n == Integer.MIN_VALUE) {
            x *= x;
            n /= 2;
        }
        // If the exponent is negative
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public double myPowIterative(double x, int n) {
        return myPow(x, n);
    }

    private double myPow(double x, long n) {
        // Base case
        if (n == 0) {
            return 1;
        }
        // For negative n
        if (n < 0) {
            n = -n;
            x = 1.0 / x;
        }
        // Perform binary exponentiation
        double result = 1.0;
        while (n != 0) {
            // For odd n
            if (n % 2 == 1) {
                result *= x;
                n -= 1;
            }
            // (x^2) ^ (n/2) = x^n
            x *= x;
            n /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        final PowXn powXn = new PowXn();

        double x = 2.00000;
        int n = 10;
        System.out.println(powXn.myPowRecursive(x, n));
        System.out.println(powXn.myPowIterative(x, n));

        x = 2.10000;
        n = 3;
        System.out.println(powXn.myPowRecursive(x, n));
        System.out.println(powXn.myPowIterative(x, n));

        x = 2.00000;
        n = -2;
        System.out.println(powXn.myPowRecursive(x, n));
        System.out.println(powXn.myPowIterative(x, n));
    }
}
