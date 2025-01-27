package org.redquark.leetcoding.math;

public class PowXn {

    public double myPow(double x, int n) {
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

    public static void main(String[] args) {
        final PowXn powXn = new PowXn();

        double x = 2.00000;
        int n = 10;
        System.out.println(powXn.myPow(x, n));

        x = 2.10000;
        n = 3;
        System.out.println(powXn.myPow(x, n));

        x = 2.00000;
        n = -2;
        System.out.println(powXn.myPow(x, n));
    }
}
