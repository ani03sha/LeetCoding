package org.redquark.leetcoding.bitmanipulation;

public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while (b != 0) {
            final int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        final SumOfTwoIntegers sumOfTwoIntegers = new SumOfTwoIntegers();

        System.out.println(sumOfTwoIntegers.getSum(3, 2));
        System.out.println(sumOfTwoIntegers.getSum(5, 7));
    }
}
