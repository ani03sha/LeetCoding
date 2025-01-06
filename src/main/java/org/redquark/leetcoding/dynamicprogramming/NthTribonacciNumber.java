package org.redquark.leetcoding.dynamicprogramming;

public class NthTribonacciNumber {

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int c = 1;
        int d;
        for (int i = 3; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    public static void main(String[] args) {
        final NthTribonacciNumber nthTribonacciNumber = new NthTribonacciNumber();

        System.out.println(nthTribonacciNumber.tribonacci(1));
        System.out.println(nthTribonacciNumber.tribonacci(2));
        System.out.println(nthTribonacciNumber.tribonacci(13));
        System.out.println(nthTribonacciNumber.tribonacci(25));
        System.out.println(nthTribonacciNumber.tribonacci(37));
    }
}
