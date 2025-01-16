package org.redquark.leetcoding.math;

public class PowXn {

    public double myPow(double x, int n) {
        // Special case
        if (n == 0) {
            return 1;
        }
        // Divide the power by half and recurse
        double temp = myPow(x, n / 2);
        // If the power is a multiple of 2
        if (n % 2 == 0) {
            return temp * temp;
        }
        return n > 0 ? (x * temp * temp) : (temp * temp / x);
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
